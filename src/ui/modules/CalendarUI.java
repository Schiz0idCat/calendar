package ui.modules;

import java.util.Scanner;
import modules.calendar.Calendar;

public class CalendarUI {
    public void run(Scanner scan, Calendar calendar) {
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
                case 2: // mostrar un solo evento
                    this.listOneEvent();
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
        System.out.println("Listing all events...");
    }

    private void listOneEvent() {
        System.out.println("Listing one event...");
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
