package ui.modules;

import config.Config;
import config.modules.CalendarConfig;
import modules.calendar.Calendar;
import modules.calendar.Event;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CalendarUI {
    private Calendar calendar;
    private Scanner scan;

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

            switch (option) {
                case 1: // buscar eventos por nombre
                    List<Event> resultName = this.searchEventsByName();

                    for(Event e : resultName) {
                        System.out.println(e.toString());
                    }

                    break;
                case 2: // buscar eventos por fecha
                    List<Event> resultDate = this.searchEventsByDate();

                    for(Event e : resultDate) {
                        System.out.println(e.toString());
                    }

                    break;
                case 6: // salir de la interfaz
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    private List<Event> searchEventsByName() {
        System.out.println("Please, enter a keyword");

        String keyword = scan.nextLine();
        List<Event> result = calendar.searchByTitle(keyword);

        return result;
    }

    private List<Event> searchEventsByDate() {
        List<Event> result = new ArrayList<>();
        System.out.println("Please, enter a date");
        String strDate;

        strDate = scan.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(config.getDateFormat());
        LocalDate date = null;

        try {
            date = LocalDate.parse(strDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. The format is: " + config.getDateFormat());
        }

        if (date != null) {
            result = calendar.searchByDate(date);
        }
        
        return result;
    }
    //alguna otra forma de que no sea asi?
    private void showEventFields(Event event){
        System.out.println("\nCurrent Event Fields:");

        String title = "empty";
        if (event.getTitle() != null) {
            title = event.getTitle();
        }
        System.out.println("1. Title: " + title);

        String description = "empty";
        if (event.getDescription() != null) {
            description = event.getDescription();
        }
        System.out.println("2. Description: " + description);

        String dateStr = "empty";
        if (event.getDate() != null) {
            dateStr = event.getDate().format(DateTimeFormatter.ofPattern(config.getDateFormat()));
        }
        System.out.println("3. Date: " + dateStr);

        String time = "empty";
        if (event.getTime() != null) {
            time = event.getTime();
        }
        System.out.println("4. Time: " + time);

        String location = "empty";
        if (event.getLocation() != null) {
            location = event.getLocation();
        }
        System.out.println("5. Location: " + location);

        System.out.println("6. Save and Exit");
        System.out.println("7. Exit without saving");

    }
    private Event editEventFields(Event event){
        boolean editing = true;

        while (editing) {
            this.showEventFields(event);
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
                    event.setTitle(title);
                    break;
                case 2: 
                    System.out.print("Enter new description: ");
                    String description = scan.nextLine();
                    event.setDescription(description);
                    break;
                case 3: 
                    System.out.print("Enter new date (" + config.getDateFormat() + "): ");
                    String strDate = scan.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(config.getDateFormat());
                    LocalDate date = null;

                    try {
                        date = LocalDate.parse(strDate, formatter);
                        event.setDate(date);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date. The format is: " + config.getDateFormat());
                    }
                    break;
                case 4: 
                    System.out.print("Enter new time (HH:mm): ");
                    String time = scan.nextLine();
                    event.setTime(time);
                    break;
                case 5: 
                    System.out.print("Enter new location: ");
                    String location = scan.nextLine();
                    event.setLocation(location);
                    break;
                case 6: 
                    System.out.println("Saving changes and exiting...");
                    editing = false;
                    return event;
                case 7: 
                    System.out.println("Exiting without saving...");
                    editing = false;
                    return null;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
        return null;
    }

    private Event selectedEvent(List<Event> events) {
        if (events.isEmpty()) {
            System.out.println("No events found.");
            return null;
        }

        System.out.println("Select an event by its number:");

        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i).toString());
        }

        System.out.print("Enter the number of the event: ");
        int selection;

        try {
            selection = Integer.parseInt(scan.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Please, enter a valid number.");
            return null;
        }

        if (selection < 1 || selection > events.size()) {
            System.out.println("Invalid selection.");
            return null;
        }

        return events.get(selection - 1);
    }
    
    private void enterNewEvent() {
        Event newEvent = new Event();
        Event result = editEventFields(newEvent);

        if (result != null) {
            calendar.addEvent(result);
            System.out.println("Event added successfully.");
        }
    }

    private void modifyEvent(){
        System.out.println("Search for the event you want to modify...");
        List<Event> events = this.searchEventsByName();

        Event eventToModify = this.selectedEvent(events);

        if (eventToModify != null) {
            Event modifiedEvent = this.editEventFields(eventToModify);

            if (modifiedEvent != null) {
                calendar.updateEvent(eventToModify, modifiedEvent);
                System.out.println("Event modified successfully.");
            }
        }
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

        Event eventToDelete = this.selectedEvent(events);

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
}
