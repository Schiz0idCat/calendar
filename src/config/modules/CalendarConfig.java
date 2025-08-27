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

    // Setters
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public void setWeekStart(String weekStart) {
        this.weekStart = weekStart;
    }

    // Setters to default
    public void setDateFormat() {
        this.dateFormat = "dd-MM-yyyy";
    }

    public void setTimeFormat() {
        this.timeFormat = "HH:mm";
    }

    public void setWeekStart() {
        this.weekStart = "MONDAY";
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
