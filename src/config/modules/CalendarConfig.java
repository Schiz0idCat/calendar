package config.modules;

public class CalendarConfig {
    private String dateFormat;
    private String timeFormat;
    private String weekStart;

    // Getters
    public String getDateFormat() {
        return this.dateFormat;
    }

    public String getTimeFormat() {
        return this.timeFormat;
    }

    public String getWeekStart() {
        return this.weekStart;
    }

    // Formatter
    @Override
    public String toString() {
        return "[calendar]" + "\n" +
        "dateFormat = \"" + this.getDateFormat() + "\"" + "\n" +
        "timeFormat = \"" + this.getTimeFormat() + "\"" + "\n" +
        "weekStart = \"" + this.getWeekStart() + "\"";
    }
}
