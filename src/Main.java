import ui.UI;
import modules.calendar.Calendar;

public class Main {
    public static void main(String[] args) {
        final Calendar calendar = new Calendar();

        UI.run(calendar);
    }
}
