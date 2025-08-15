package config.modules;

public class CalendarConfig {
    private String dateFormat;
    private String timeFormat;

    // Getters
    public String getDateFormat() {
        return dateFormat;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    // Formatter
    @Override
    public String toString() {
        return "[calendar]" + "\n" +
        "dateFormat = \"" + this.getDateFormat() + "\"" + "\n" +
        "timeFormat = \"" + this.getTimeFormat() + "\"";
    }
}
