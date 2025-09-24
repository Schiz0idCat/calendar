import ui.CLI.MainCLI;
import modules.calendar.Calendar;
import modules.people.People;

public class Main {
    public static void main(String[] args) {
        final Calendar calendar = new Calendar();
        final People people = new People();

        MainCLI.run(args, calendar, people);
    }
}
