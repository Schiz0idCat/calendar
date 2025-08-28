package ui;

import ui.modules.CalendarUI;
import ui.modules.PeopleUI;
import modules.calendar.Calendar;
import modules.people.People;

import java.util.Scanner;

public class UI {
    public static void run(Calendar calendar, People people) {
        Scanner scan = new Scanner(System.in);
        CalendarUI calendarUI = new CalendarUI();
        PeopleUI peopleUI = new PeopleUI();
        boolean running = true;

        while (running) {
            System.out.println("=====> Main Menu <=====");
            System.out.println("What do you want to do?");
            System.out.println("1. Calendar.");
            System.out.println("2. People.");
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
                    calendarUI.run(scan, calendar);
                    break;
                case 2: // personas
                    peopleUI.run(scan, people);
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
