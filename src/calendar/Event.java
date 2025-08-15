package calendar;

import config.Config;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String description;

    String dateFormat = Config.get("calendar.dateFormat");
    String timeFormat = Config.get("calendar.timeFormat");

    // Constructor
    public Event(String title, LocalDate date, LocalTime startTime, LocalTime endTime, String location, String description) {
        this.setTitle(title);
        this.setDate(date);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setLocation(location);
        this.setDescription(description);
    }

    // Getters
    public String getTitle() {
        return this.title;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public String getLocation() {
        return this.location;
    }

    public String getDescription() {
        return this.description;
    }

    // Setters
    public void setTitle(String title) {

    }

    public void setDate(LocalDate date) {

    }

    public void setStartTime(LocalTime startTime) {

    }

    public void setEndTime(LocalTime endTime) {

    }

    public void setLocation(String location) {

    }

    public void setDescription(String description) {

    }

    // Formatters
    public String fmtDate(String pattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);

        return this.getDate().format(format);
    }

    public String fmtStartTime(String pattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);

        return this.getStartTime().format(format);
    }

    public String fmtEndTime(String pattern) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);

        return this.getEndTime().format(format);
    }

    @Override
    public String toString() {
        return "Event: " + this.getTitle() + "\n" +
        "Date: " + this.fmtDate(dateFormat) + "\n" +
        "Time: " + this.fmtStartTime(timeFormat) + " - " + this.fmtEndTime(timeFormat) + "\n" +
        "Location: " + this.getLocation() + "\n" +
        "Description: " + this.getDescription();
    }
}
