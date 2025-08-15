import config.Config;

public class Main {
    public static void main(String[] args) {
        System.out.println("THIS IS A TEST");

        final Config config = Config.load();

        System.out.println(config.getCalendar());
    }
}
