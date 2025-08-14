package calendar;

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

    }

    public String getLocation() {

    }

    public String getDescription() {

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
    public String fmtDate() {

    }

    public String fmtStartTime() {

    }

    public String fmtEndTime() {

    }

    @Override
    public String toString() {
        return "Event: " + this.getTitle() + "\n" +
               "Date: " + this.fmtDate() + "\n" +
               "Time: " + this.fmtStartTime() + " - " + this.fmtEndTime() + "\n" +
               "Location: " + this.getLocation() + "\n" +
               "Description: " + this.getDescription();
    }
}
