package ui;

import ui.modules.CalendarUI;

import java.util.Scanner;

public class UI {
    public static void run() {
        Scanner scan = new Scanner(System.in);
        CalendarUI calendarUI = new CalendarUI();
        boolean running = true;

        while (running) {
            System.out.println("=====> Main Menu <=====");
            System.out.println("What do you want to do?");
            System.out.println("1. Calendar.");
            System.out.println("2. Exit.");
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
                case 1: // calendario
                    calendarUI.run(scan);
                    break;
                case 2: // salir de la interfaz
                    System.out.println("Goodbye.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }

        scan.close();
    }
}
