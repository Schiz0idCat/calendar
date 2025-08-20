package modules.calendar;

import config.Config;
import config.modules.CalendarConfig;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isAllDay;
    private Recurrence recurrence;
    private String location;
    private String description;

    private static final CalendarConfig config = Config.load().getCalendar();

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

    public boolean getIsAllDay() {
        return this.isAllDay;
    }

    public Recurrence getRecurrence() {
        return this.recurrence;
    }

    public String getLocation() {
        return this.location;
    }

    public String getDescription() {
        return this.description;
    }

    // Setters
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("El titulo no puede estar vacio");
        }
        this.title = title.trim();

    }

    public void setDate(LocalDate date) {
        if(date == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        this.date = date;
    }

    public void setStartTime(LocalTime startTime) {
        if(startTime == null && !isAllDay) {
            throw new IllegalArgumentException("La hora de inicio no puede ser nula para eventos con horario");
        }
        this.startTime = startTime;
        validateTimeRange();
    }

    public void setEndTime(LocalTime endTime) {
        if(endTime == null && !isAllDay) {
            throw new IllegalArgumentException("La hora de fin no puede ser nula para eventos con horario");
        }
        this.endTime = endTime;
        validateTimeRange();
    }

    public void setIsAllDay(boolean isAllDay) {
        this.isAllDay = isAllDay;
        if (isAllDay) {
            this.startTime = LocalTime.MIN;
            this.endTime = LocalTime.MAX;
        }
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    public void setLocation(String location) {
        this.location = location != null ? location.trim() : "";
    }

    public void setDescription(String description) {
        this.description = description != null ? description.trim() : "";
    }

    private void validateTimeRange() {
        if(!isAllDay && startTime != null && endTime != null && startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("La hora de inicio no puede ser posterior a la hora de fin");
        }
        if (isAllDay && (startTime != LocalTime.MIN || endTime != LocalTime.MAX)) {
            throw new IllegalArgumentException("Para eventos de todo el día, la hora de inicio debe ser 00:00 y la hora de fin debe ser 23:59");
        }
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
        "Date: " + this.fmtDate(config.getDateFormat()) + "\n" +
        "Time: " + this.fmtStartTime(config.getTimeFormat()) + " - " + this.fmtEndTime(config.getTimeFormat()) + "\n" +
        "Location: " + this.getLocation() + "\n" +
        "Description: " + this.getDescription();
    }
}
