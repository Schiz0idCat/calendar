package ui.TUI.modules;

import config.Config;
import config.modules.CalendarConfig;
import modules.calendar.Calendar;
import disk.modules.CSVCalendar;
import disk.modules.CSVPeople;
import errors.modules.calendar.*;
import modules.calendar.Event;
import modules.people.People;
import modules.people.Person;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CalendarUI {
    private Calendar calendar;
    private People people;
    private Scanner scan;
    private DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern(config.getDateFormat());
    private DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern(config.getTimeFormat());
    private CSVCalendar csv;

    private static final CalendarConfig config = Config.load().getCalendar();

    public void run(Scanner scan, Calendar calendar, CSVCalendar csvCalendar, CSVPeople csvPeople) {
        this.calendar = calendar;
        this.people = csvPeople.load();
        this.scan = scan;
        this.csv = csvCalendar;
        this.calendar = csvCalendar.load();

        boolean running = true;

        while (running) {
            System.out.println("=====> Calendar <=====");
            System.out.println("What do you want to do?");
            System.out.println("1. List all events.     2. Search an event.");
            System.out.println("3. Add an event.        4. Modify an event.");
            System.out.println("5. Remove an event.     6. Exit.");
            System.out.print("Select an option: ");

            int option;

            try {
                option = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Please, enter a valid number.");
                continue;
            }

            System.out.println();

            switch (option) {
                case 1: // mostrar todos los eventos
                    this.listAllEvents();
                    break;
                case 2: // mostrar por coincidencia
                    this.searchEvents();
                    break;
                case 3: // ingresar un nuevo evento
                    this.enterNewEvent();
                    break;
                case 4: // modificar un evento
                    this.modifyEvent();
                    break;
                case 5: // eliminar un evento
                    this.deleteEvent();
                    break;
                case 6: // salir de la interfaz
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }

            System.out.println();
        }
    }

    private void listAllEvents() {
        System.out.println("Listing all events:");
        System.out.println(calendar.toString());
    }

    private void searchEvents() {
        boolean running = true;

        while (running) {
            System.out.println("How do you want to search?");
            System.out.println("1. Event Name.");
            System.out.println("2. Event Date.");
            System.out.println("3. Exit.");
            System.out.print("Select an option: ");
            int option;

            try {
                option = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Please, enter a valid number.");
                continue;
            }

            System.out.println();

            switch (option) {
                case 1: // buscar eventos por nombre
                    List<Event> resultName = this.searchEventsByName();

                    if (resultName.isEmpty()) {
                        System.out.println("No coincidences were found.");
                    }
                    else {
                        for(Event e : resultName) {
                            System.out.println(e.toString());
                            System.out.println();
                        }
                    }

                    break;
                case 2: // buscar eventos por fecha
                    List<Event> resultDate = this.searchEventsByDate();

                    if (resultDate.isEmpty()) {
                        System.out.println("No coincidences were found.");
                    }
                    else {
                        for(Event e : resultDate) {
                            System.out.println(e.toString());
                            System.out.println();
                        }
                    }

                    break;
                case 3: // salir de la interfaz
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }

            System.out.println();
        }
    }

    private void enterNewEvent() {
        Event newEvent = new Event();
        this.editEventFields(newEvent);

        if (calendar.add(newEvent)) {
            this.csv.save(calendar);
            System.out.println("Event added successfully.");
        }
        else {
            System.out.println("The event was not added due conflicts with another event.");
        }
    }

    private void modifyEvent(){
        boolean running = true;
        List<Event> events = null;

        while (running) {
            System.out.println("Search for the event you want to modify.");
            System.out.println("Search event by:");
            System.out.println("1. Name");
            System.out.println("2. Date");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int option;

            try {
                option = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Please, enter a valid number.");
                return;
            }

            switch (option) {
                case 1: // Buscar por nombre
                    events = this.searchEventsByName();
                    break;
                case 2: // Buscar por fecha
                    events = this.searchEventsByDate();
                    break;
                case 3: // salir de la interfaz
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }

            if (!events.isEmpty()) running = false;
        }

        Event eventToModify = this.selectAnEvent(events);

        if (eventToModify == null) {
            System.out.println("No Event was selected.");
            return;
        }

        this.editEventFields(eventToModify);
        this.csv.save(calendar);
        System.out.println("Event modified successfully.");
    }

    private void deleteEvent() {
        boolean running = true;
        List<Event> events = null;

        while (running) {
            System.out.println("Search for the event you want to delete.");
            System.out.println("Search event by:");
            System.out.println("1. Name");
            System.out.println("2. Date");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int option;

            try {
                option = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Please, enter a valid number.");
                return;
            }

            switch (option) {
                case 1: // Buscar por nombre
                    events = this.searchEventsByName();
                    break;
                case 2: // Buscar por fecha
                    events = this.searchEventsByDate();
                    break;
                case 3: // salir de la interfaz
                    System.out.println("Exiting...");
                    running = false;
                break;
                default:
                    System.out.println("Invalid option, try again.");
            }

            if (!events.isEmpty()) running = false;
        }

        Event eventToDelete = this.selectAnEvent(events);

        if (eventToDelete == null) {
            System.out.println("No Event was selected.");
            return;
        }

        System.out.println("\n" + eventToDelete.toString());
        System.out.print("Are you sure you want to delete this event? (y/n): ");

        String confirmation = scan.nextLine();

        if(confirmation.strip().equalsIgnoreCase("y")) {
            calendar.remove(eventToDelete);
            this.csv.save(calendar);
            System.out.println("Event deleted successfully.");
        }
    }

    private void editEventFields(Event event) {
        boolean editing = true;

        while (editing) {
            System.out.println();

            System.out.println("\nEvent details:");
            System.out.println("1. Title: " + event.getTitle());
            System.out.println("2. Date: " + event.fmtDate(config.getDateFormat()));
            System.out.println("3. Start Time: " + event.fmtStartTime(config.getTimeFormat()));
            System.out.println("4. End Time: " + event.fmtEndTime(config.getTimeFormat()));
            System.out.println("5. All Day: " + event.getIsAllDay());
            System.out.println("6. Location: " + event.getLocation());
            System.out.println("7. Description: " + event.getDescription());
            System.out.println("8. Participants:");

            if (!event.getParticipants().isEmpty()) {
                for (Person person : event.getParticipants().values()) {
                    System.out.println("   - " + person.getName() + " (" + person.getRut() + ")");
                }
            }

            System.out.println("9. Exit");
            System.out.print("Select an option to edit: ");
            int option;

            try {
                option = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Please, enter a valid number.");
                continue;
            }

            switch (option) {
                case 1: 
                    System.out.print("Enter new title: ");
                    String title = scan.nextLine();

                    try {
                        event.setTitle(title);
                    }
                    catch (InvalidTitleException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;
                case 2: 
                    System.out.print("Enter new date (" + config.getDateFormat() + "): ");
                    String strDate = scan.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(config.getDateFormat());
                    LocalDate date = null;

                    try {
                        date = LocalDate.parse(strDate, formatter);
                        event.setDate(date);
                    }
                    catch (InvalidDateException e) {
                        System.out.println("Invalid date. The format is: " + config.getDateFormat());
                    }

                    break;
                case 3: 
                    System.out.print("Enter new start time (" + config.getTimeFormat() + "): ");
                    String strStartTime = scan.nextLine();

                    try {
                        LocalTime startTime = LocalTime.parse(strStartTime, this.fmtTime);
                        event.setStartTime(startTime);
                    }
                    catch (InvalidTimeException e) {
                        System.out.println("Time format incorrect. The format is: " + config.getTimeFormat());
                    }

                    break;
                case 4: 
                    System.out.print("Enter new end time (" + config.getTimeFormat() + "): ");
                    String strEndTime = scan.nextLine();

                    try {
                        LocalTime endTime = LocalTime.parse(strEndTime, this.fmtTime);
                        event.setEndTime(endTime);
                    }
                    catch (InvalidTimeException e) {
                        System.out.println("Time format incorrect. The format is: " + config.getTimeFormat());
                    }

                    break;
                case 5: 
                    System.out.print("Enter if it is an all day event (true/false): ");
                    String strBool = scan.nextLine();

                    try {
                        if (!strBool.equalsIgnoreCase("true") && !strBool.equalsIgnoreCase("false")) {
                            throw new IllegalArgumentException();
                        }

                        boolean allDay = Boolean.parseBoolean(strBool);
                        event.setIsAllDay(allDay);

                    } catch (IllegalArgumentException e) {
                        System.out.println("You must enter 'true' or 'false'");
                    }

                    break;
                case 6: 
                    System.out.print("Enter new location: ");
                    String location = scan.nextLine();
                    event.setLocation(location);

                    break;
                case 7: 
                    System.out.print("Enter new description: ");
                    String description = scan.nextLine();
                    event.setDescription(description);

                    break;
                case 8: 
                    if (people.isEmpty()) {
                        System.out.println("There are no people registered.");
                        continue;
                    }

                    this.modifyParticipants(event);
                    break;
                case 9:
                    System.out.println("Saving changes and exiting...");
                    editing = false;

                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    private void modifyParticipants(Event event) {
        boolean running = true;

        while (running) {
            System.out.println("\nParticipants:");

            for (Person person : event.getParticipants().values()) {
                System.out.println(" - " + person.getName() + " (" + person.getRut() + ")");
            }

            System.out.println("1. Add participant.");
            System.out.println("2. Remove participant.");
            System.out.println("3. Exit.");
            System.out.print("Select an option to edit: ");

            int option;
            try {
                option = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Please, enter a valid number.");
                continue;
            }

            switch (option) {
                case 1:
                    this.addParticipant(event);
                    break;
                case 2:
                    this.removeParticipant(event);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    private void addParticipant(Event event) {
        System.out.print("Enter RUT of the person to add: ");
        String rut = scan.nextLine().trim();

        Person person = people.get(rut);

        if (person != null) {
            try {
                event.addParticipant(person);
                System.out.println("Participant added: " + person.getName());
            }
            catch (InvalidParticipantException e) {
                System.out.println("Error adding participant: " + e.getMessage());
            }
        }
        else {
            System.out.println("No person found with RUT: " + rut);
        }
    }

    private void removeParticipant(Event event) {
        if (event.getParticipants().isEmpty()) {
            System.out.println("No participants to remove.");
            return;
        }

        System.out.print("Enter RUT of the person to remove: ");
        String rut = scan.nextLine().trim();

        if (event.getParticipants().containsKey(rut)) {
            event.removeParticipant(rut);
            System.out.println("Participant removed.");
        }
        else {
            System.out.println("No participant found with RUT: " + rut);
        }
    }

    private Event selectAnEvent(List<Event> events) {
        if (events.isEmpty()) {
            System.out.println("No events found.");
            return null;
        }

        boolean running = true;
        Event selectedEvent = null;

        while (running) {
            System.out.println("Select an event by its number:");

            int index = 1;
            for (Event e: events) {
                System.out.println((index) + ". \n" + e.toString() + "\n");
                index++;
            }

            System.out.println(index + " Exit.");

            System.out.print("Enter the number of the event: ");
            int selection;
            try {
                selection = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Please, enter a valid number.");
                continue;
            }

            if (selection == index) {
                System.out.println("Exiting...");
                return null;
            }
            else if (selection < 1 || selection > events.size()) {
                System.out.println("Invalid selection.");
                continue;
            }

            selectedEvent = events.get(selection - 1);
            running = false;
        }

        return selectedEvent;
    }

    private List<Event> searchEventsByName() {
        System.out.print("Please, enter a keyword: ");
        String keyword = scan.nextLine();

        List<Event> result = new ArrayList<>();

        try {
            result = calendar.searchByTitle(keyword);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return result;
    }

    private List<Event> searchEventsByDate() {
        List<Event> result = new ArrayList<>();
        System.out.print("Please, enter a date: ");
        String strDate;

        strDate = scan.nextLine();
        LocalDate date = null;

        try {
            date = LocalDate.parse(strDate, fmtDate);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. The format is: " + config.getDateFormat());
        }

        if (date != null) {
            result = calendar.searchByDate(date);
        }

        return result;
    }
}
