package ui;

import ui.modules.CalendarUI;
import ui.modules.AddressBookUI;

import java.util.Scanner;

public class UI {
    public static void run() {
        Scanner scan = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=====> Main Menu <=====");
            System.out.println("What do you want to do?");
            System.out.println("1. Calendar.");
            System.out.println("2. Contacts.");
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
                case 1: // calendario
                    CalendarUI.run(scan);
                    break;
                case 2: // agenda de contactos
                    AddressBookUI.run(scan);
                    break;
                case 3: // salir de la interfaz
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
