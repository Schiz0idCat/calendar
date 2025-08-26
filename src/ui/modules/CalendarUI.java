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

        try {
            LocalDate date = LocalDate.parse(strDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. The format is: " + config.getDateFormat());
        }

        result = calendar.searchByDate(date);
        
        return result;
    }

    private void enterNewEvent() {
        System.out.println("Enter the new event...");
    }

    private void modifyEvent() {
        System.out.println("Search for the event you want to modify...");
    }

    private void deleteEvent() {
        System.out.println("Search for the event you want to delete...");
    }
}
