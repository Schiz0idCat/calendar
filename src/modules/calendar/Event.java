package modules.calendar;

import config.Config;
import config.modules.CalendarConfig;
import modules.people.Person;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Event {
    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isAllDay;
    private String location;
    private String description;
    private Map<String, Person> participants = new HashMap<>();

    private static final CalendarConfig config = Config.load().getCalendar();
    private static final LocalTime END_OF_DAY = LocalTime.of(23, 59);

    // Constructor
    public Event() {
        this.setTitle();
        this.setDate();
        this.setIsAllDay();
        this.setLocation();
        this.setDescription();
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

    public String getLocation() {
        return this.location;
    }

    public String getDescription() {
        return this.description;
    }

    // Setters
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title.trim();
    }

    public void setTitle() {
        this.title = "default title";
    }

    public void setDate(LocalDate date) {
        if(date == null) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        this.date = date;
    }

    public void setDate() {
        this.date = LocalDate.now();
    }

    public void setStartTime(LocalTime startTime) {
        if(startTime == null) {
            throw new IllegalArgumentException("Start time cannot be null for non-all-day events");
        }
        if (this.endTime != null && startTime.isAfter(this.endTime)) {
            throw new IllegalArgumentException("Start time cannot be after end time");
        }
        if(startTime == LocalTime.MIN && endTime == END_OF_DAY){
            this.isAllDay = true;
        }
        else {
            this.isAllDay = false;
        }
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        if(endTime == null) {
            throw new IllegalArgumentException("End time cannot be null for non-all-day events");
        }
        if (this.startTime != null && endTime.isBefore(this.startTime)) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }
        if(endTime == END_OF_DAY && startTime == LocalTime.MIN){
            this.isAllDay = true;
        }
        else {
            this.isAllDay = false;
        }
        this.endTime = endTime;
    }

    public void setIsAllDay(boolean isAllDay) {
        this.isAllDay = isAllDay;
        if (isAllDay) {
            this.startTime = LocalTime.MIN;
            this.endTime = END_OF_DAY;
        }
    }

    public void setIsAllDay() {
        this.isAllDay = true;
        this.startTime = LocalTime.MIN;
        this.endTime = END_OF_DAY;
    }

    public void setLocation(String location) {
        if (location == null){
            this.location = "";
            return;
        }
        this.location = location.trim();
    }

    public void setLocation() {
        this.location = "";
    }

    public void setDescription(String description) {
        if (description == null){
            this.description = "";
            return;
        }
        this.description = description.trim();
    } 

    public void setDescription() {
            this.description = "";
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

    // Participants
    public boolean addParticipant(Person person) {
        if (person != null) {
            this.participants.put(person.getRut(), person);
            return true;
        }

        return false;
    }

    public void removeParticipant(String rut) {
        this.participants.remove(rut);
    }

    public Map<String, Person> getParticipants() {
        return participants;
    }


    @Override
    public String toString() {
        StringBuilder event = new StringBuilder();

        event.append("Event: " + this.getTitle() + "\n" +
        "Date: " + this.fmtDate(config.getDateFormat()) + "\n" +
        "Start Time: " + this.fmtStartTime(config.getTimeFormat()) + "\n" +
        "End Time: " + this.fmtEndTime(config.getTimeFormat()) + "\n" +
        "Is All Day: " + this.getIsAllDay() + "\n" +
        "Location: " + this.getLocation() + "\n" +
        "Description: " + this.getDescription());

        if (this.getParticipants().size() == 0) return event.toString();

        event.append("Participants:\n");
        for (Person person : this.getParticipants().values()) {
            event.append("- ").append(person.getName()).append(" (").append(person.getRut()).append(")\n");
        }

        return event.toString();
    }
}
