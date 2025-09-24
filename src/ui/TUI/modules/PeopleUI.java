package ui.TUI.modules;

import errors.modules.people.*;
import disk.modules.CSVCalendar;
import modules.calendar.Calendar;
import modules.calendar.Event;
import disk.modules.CSVPeople;
import modules.people.People;
import modules.people.Person;

import java.util.Scanner;

public class PeopleUI {
    private People people;
    private Calendar calendar;
    private CSVPeople csvPeople;
    private CSVCalendar csvCalendar;
    private Scanner scan;

    public void run(Scanner scan, Calendar calendar, CSVPeople csvPeople, CSVCalendar csvCalendar) {
        this.scan = scan;
        this.calendar = calendar;
        this.csvPeople = csvPeople;
        this.csvCalendar = csvCalendar;
        this.people = csvPeople.load(); // cargar al inicio

        boolean running = true;

        while (running) {
            System.out.println("=====> People <=====");
            System.out.println("What do you want to do?");
            System.out.println("1. List all people.   2. Search a person by RUT.");
            System.out.println("3. Add a person.      4. Modify a person.");
            System.out.println("5. Remove a person.   6. Exit.");
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
                case 1:
                    listAllPeople();
                    break;
                case 2:
                    searchPerson();
                    break;
                case 3:
                    addPerson();
                    break;
                case 4:
                    modifyPerson();
                    break;
                case 5:
                    removePerson();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }

            System.out.println();
        }
    }

    private void listAllPeople() {
        if (this.people.isEmpty()) {
            System.out.println("There is no people registered.");
            return;
        }

        System.out.println("Listing all people:\n");
        System.out.println(this.people.toString());
    }

    private void searchPerson() {
        if (this.people.isEmpty()) {
            System.out.println("There is no people registered.");
            return;
        }

        System.out.print("Enter RUT to search: ");
        String rut = scan.nextLine();
        Person person = this.people.get(rut);

        if (person == null) {
            System.out.println("No person found with RUT: " + rut);
        } else {
            System.out.println(person.toString());
        }
    }

    private void addPerson() {
        Person person = new Person();
        
        try {
            this.modifyPerson(person);
            this.people.add(person);
            csvPeople.save(people);
            System.out.println("Person added successfully.");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void modifyPerson() {
        System.out.print("Enter RUT of the person to modify: ");
        String rut = scan.nextLine();
        Person person = this.people.get(rut);

        if (person == null) {
            System.out.println("No person found with RUT: " + rut);
            return;
        }

        editPerson(person);
        csvPeople.save(people);
    }

    private void modifyPerson(Person person) {
        editPerson(person);
    }

    private void editPerson(Person person) {
        boolean editing = true;
        while (editing) {
            System.out.println("\nPerson details:");
            System.out.println("1. RUT: " + person.getRut());
            System.out.println("2. Name: " + person.getName());
            System.out.println("3. Email: " + person.getEmail());
            System.out.println("4. Phone: " + person.getPhone());
            System.out.println("5. Exit");
            System.out.print("Select field to modify: ");

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
                    System.out.print("Enter new RUT: ");
                    String rut = scan.nextLine();

                    if (this.people.contains(rut)) {
                        System.out.println("Error: a person with that RUT already exists");
                        break;
                    }

                    try {
                        person.setRut(rut);
                    }
                    catch (InvalidRutException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter new name: ");
                    try {
                        person.setName(scan.nextLine());
                    }
                    catch (InvalidNameException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter new email: ");
                    try {
                        person.setEmail(scan.nextLine());
                    }
                    catch (InvalidEmailException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter new phone: ");

                    try {
                        String phone = scan.nextLine();
                        person.setPhone(phone);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("phone must be a valid number");
                    }

                    break;
                case 5:
                    System.out.println("Saving changes and exiting...");
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    private void removePerson() {
        System.out.print("Enter RUT of the person to remove: ");
        String rut = scan.nextLine();
        Person p = this.people.get(rut);

        if (p == null) {
            System.out.println("No person found with RUT: " + rut);
            return;
        }

        System.out.println("\n" + p.toString());
        System.out.print("Are you sure you want to delete this person? (y/N): ");
        String confirmation = scan.nextLine();

        if (confirmation.strip().equalsIgnoreCase("y")) {
            this.people.remove(rut);
            this.csvPeople.save(people);

            for (Event e : this.calendar.getAllEvents()) {
                e.removeParticipant(rut);
            }

            this.csvCalendar.save(calendar);
            System.out.println("Person removed successfully.");
        }
        else {
            System.out.println("Person was NOT removed.");
        }
    }
}
