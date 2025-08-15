package ui.modules;

import java.util.Scanner;

public class ContactsUI {
    public static void run(Scanner scan) {
        boolean running = true;

        while (running) {
            System.out.println("=====> Contacts <=====");
            System.out.println("What do you want to do?");
            System.out.println("1. List all contacts.       2. Search for a contact.");
            System.out.println("3. Add a contact.           4. Modify a contact.");
            System.out.println("5. Remove a contact.        6. Exit.");
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
                case 1: // mostrar todos los contactos
                    System.out.println("Listing all contacts...");
                    // código
                    break;
                case 2: // mostrar un solo contacto
                    System.out.println("Search by...");
                    // código
                    break;
                case 3: // ingresar un nuevo contacto
                    System.out.println("Enter the new contact...");
                    // código
                    break;
                case 4: // modificar un contacto
                    System.out.println("Find the contact you want to modify...");
                    // código
                    break;
                case 5: // eliminar un contacto
                    System.out.println("Search the contact you want to delete...");
                    // código
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
}
