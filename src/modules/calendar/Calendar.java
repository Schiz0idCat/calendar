package modules.calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Calendar {
    private final List<Event> events;

    // Constructor
    public Calendar() {
        this.events = new ArrayList<>();
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(this.events); 
    }

    private int cmpEvents(Event e1, Event e2) {
        // Comparar fecha
        int cmp = e1.getDate().compareTo(e2.getDate());
        if (cmp != 0) return cmp;

        // Comparar hora de inicio
        cmp = e1.getStartTime().compareTo(e2.getStartTime());
        if (cmp != 0) return cmp;

        // Comparar hora de fin
        cmp = e1.getEndTime().compareTo(e2.getEndTime());
        if (cmp != 0) return cmp;

        // Comparar título
        cmp = e1.getTitle().compareToIgnoreCase(e2.getTitle());
        return cmp;
    }

    public boolean add(Event event) {
        if (event == null) throw new IllegalArgumentException("Event cannot be null");

        int index = Collections.binarySearch(this.events, event, this::cmpEvents);

        if (index >= 0) return false;

        index = -index - 1;
        this.events.add(index, event);

        return true;        
    }

    public Event remove(Event event) {
        if (event == null) throw new IllegalArgumentException("Event cannot be null");

        int index = Collections.binarySearch(this.events, event, this::cmpEvents);

        if (index < 0) return null;

        return this.events.remove(index);
    }

    public List<Event> searchByTitle(String keyword) {
        if (keyword == null) throw new IllegalArgumentException("Keyword cannot be null");
        if (keyword.isBlank()) throw new IllegalArgumentException("Keyword cannot be blank");

        List<Event> result = new ArrayList<>();

        for(Event e : this.events) {
            if(e.getTitle().toLowerCase().trim().contains(keyword.toLowerCase().trim())) {
                result.add(e);
            }
        }

        return result;
    }

    public List<Event> searchByDate(LocalDate date) {
        if (date == null) throw new IllegalArgumentException("Date cannot be null");

        List<Event> result = new ArrayList<>();

        for(Event e : this.events) {
            if(e.getDate().equals(date)) {
                result.add(e);
            }
        }

        return result;  
    }

    @Override
    public String toString() {
        if(this.events.isEmpty()) return "No events in calendar";

        StringBuilder result = new StringBuilder();

        for (Event e : this.events) {
            result.append(e.toString()).append("\n\n");
        }

        return result.toString();
    }
}
