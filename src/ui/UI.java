package ui;

import disk.modules.CSVPeople;
import disk.modules.CSVCalendar;
import ui.modules.CalendarUI;
import ui.modules.PeopleUI;
import modules.calendar.Calendar;
import modules.people.People;
import utils.Report;

import java.io.IOException;
import java.util.Scanner;

public class UI {
    public static void run(Calendar calendar, People people) {
        Scanner scan = new Scanner(System.in);
        CalendarUI calendarUI = new CalendarUI();
        PeopleUI peopleUI = new PeopleUI();
        CSVPeople csvPeople;
        CSVCalendar csvCalendar;

        try {
            csvPeople = new CSVPeople("calendar");
            people = csvPeople.load();
        } catch (IOException e) {
            System.err.println("Failed to initialize CSVPeople: " + e.getMessage());
            return; // salir de la UI si no se puede acceder al CSV
        }

        try {
            csvCalendar = new CSVCalendar("calendar", people);
            calendar = csvCalendar.load();
        } catch (IOException e) {
            System.err.println("Failed to initialize CSVCalnedar: " + e.getMessage());
            return; // salir de la UI si no se puede acceder al CSV
        }

        boolean running = true;

        while (running) {
            System.out.println("=====> Main Menu <=====");
            System.out.println("What do you want to do?");
            System.out.println("1. Calendar.");
            System.out.println("2. People.");
            System.out.println("3. Generate a report.");
            System.out.println("4. Exit.");
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
                    calendarUI.run(scan, calendar, csvCalendar, csvPeople);
                    break;
                case 2: // personas
                    peopleUI.run(scan, calendar, csvPeople, csvCalendar);
                    break;
                case 3: // salir de la interfaz
                    try {
                        Report.generate(people, calendar);
                        System.out.println("System report generated successfully!");
                    } catch (IOException e) {
                        System.err.println("Failed to generate report: " + e.getMessage());
                    }
                    break;
                case 4: // salir de la interfaz
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
