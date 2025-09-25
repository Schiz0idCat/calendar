package ui.CLI;

import ui.TUI.MainTUI;
import ui.GUI.MainGUI;
import modules.calendar.Calendar;
import modules.people.People;

public class MainCLI {
    public static void run(String[] args, Calendar calendar, People people) {
        if (args.length == 0) {
            MainGUI.run(calendar, people);
            return;
        }

        switch (args[0].toLowerCase()) {
            case "tui":
                MainTUI.run(calendar, people);
                break;
            case "gui":
                MainGUI.run(calendar, people);
                break;
            default:
                System.err.println("Unrecognize command: " + args[0]);
        }
    }
}
