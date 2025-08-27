package ui.modules;

import config.Config;
import config.modules.CalendarConfig;
import modules.calendar.Calendar;
import modules.calendar.Event;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CalendarUI {
    private Calendar calendar;
    private Scanner scan;
    private DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern(config.getDateFormat());
    private DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern(config.getTimeFormat());

    private static final CalendarConfig config = Config.load().getCalendar();

    public void run(Scanner scan, Calendar calendar) {
        this.calendar = calendar;
        this.scan = scan;

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

        calendar.add(newEvent);
        System.out.println("Event added successfully.");
    }

    private void modifyEvent(){
        boolean running = true;
        List<Event> events = null;

        while (running) {
            System.out.println("Search for the event you want to modify.");
            System.out.println("Search event by:");
            System.out.println("1. Name");
            System.out.println("2. Date");
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
                case 6: // salir de la interfaz
                System.out.println("Exiting...");
                running = false;
                break;
                default:
                System.out.println("Invalid option, try again.");
            }
        }

        Event eventToModify = this.selectAnEvent(events);

        if (eventToModify == null) {
            System.out.println("No Event was selected.");
            return;
        }

        this.editEventFields(eventToModify);
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
                case 6: // salir de la interfaz
                    System.out.println("Exiting...");
                    running = false;
                break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }

        Event eventToDelete = this.selectAnEvent(events);

        if (eventToDelete == null) {
            System.out.println("No Event was selected.");
            return;
        }

        System.out.println(eventToDelete.toString());
        System.out.print("Are you sure you want to delete this event? (y/n): ");

        String confirmation = scan.nextLine();

        if(confirmation.strip().equalsIgnoreCase("y")) {
            calendar.remove(eventToDelete);
            System.out.println("Event deleted successfully.");
        }
    }

    private void editEventFields(Event event){
        boolean editing = true;

        while (editing) {
            System.out.println();

            String[] lines = event.toString().split("\n");
            StringBuilder EventIndexed = new StringBuilder();

            for (int i = 0; i < lines.length; i++) {
                EventIndexed.append(i + 1).append(". ").append(lines[i]).append("\n");
            }

            System.out.print(EventIndexed);
            System.out.println("8. Exit.");
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
                    catch (IllegalArgumentException e) {
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
                    catch (DateTimeParseException e) {
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
                    catch (DateTimeParseException e) {
                        System.out.println("Time format incorrect. The format is: " + config.getTimeFormat());
                    }

                    break;
                case 4: 
                    System.out.print("Enter new start time (" + config.getTimeFormat() + "): ");
                    String strEndTime = scan.nextLine();

                    try {
                        LocalTime endTime = LocalTime.parse(strEndTime, this.fmtTime);
                        event.setEndTime(endTime);
                    }
                    catch (DateTimeParseException e) {
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
                    System.out.println("Saving changes and exiting...");
                    editing = false;

                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    private Event selectAnEvent(List<Event> events) {
        if (events.isEmpty()) {
            System.out.println("No events found.");
            return null;
        }

        boolean running = true;
        int selection;
        Event selectedEvent = null;

        while (running) {
            System.out.println("Select an event by its number:");

            int option = 0;
            for (Event e: events) {
                System.out.println((++option + 1) + ". " + e.toString());
            }

            System.out.println(option + " Exit.");

            System.out.print("Enter the number of the event: ");

            try {
                selection = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Please, enter a valid number.");
                continue;
            }

            if (selection < 1 || selection > events.size()) {
                System.out.println("Invalid selection.");
                continue;
            }

            if (selection == option) {
                System.out.println("Exiting...");
                return null;
            }

            selectedEvent = events.get(selection - 1);
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
