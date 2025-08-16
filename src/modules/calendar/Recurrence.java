package modules.calendar;

import config.Config;
import config.modules.CalendarConfig;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import java.util.Set;

public class Recurrence {
    private Frequency frequency;    // frecuencia del evento (ninguna, diaria, semanal, mensual, anual)
    private int interval;           // cada cuanto {frecuencia}, se repite el evento (p.e. cada 3 meses)
    private LocalDate until;        // la fecha cuando termina la repetición
    private Set<DayOfWeek> byDay;   // los días de la semana en los que aparece el evento

    private static final CalendarConfig config = Config.load().getCalendar();

    public Recurrence(Frequency frequency, int interval, LocalDate until, Set<DayOfWeek> byDay) {
        this.setFrequency(frequency);
        this.setInterval(interval);
        this.setUntil(until);
        this.setByDay(byDay);
    }

    // Getters
    public Frequency getFrequency() {
        return this.frequency;
    }

    public int getInterval() {
        return this.interval;
    }

    public LocalDate getUntil() {
        return this.until;
    }

    public Set<DayOfWeek> getByDay() {
        return this.byDay;
    }

    // Setters
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setUntil(LocalDate until) {
        this.until = until;
    }

    public void setByDay(Set<DayOfWeek> byDay) {
        this.byDay = byDay;
    }

    // Formatters
    public String fmtFrequency() {
        return this.frequency.toString().toLowerCase();
    }

    public String fmtUntil(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return this.until.format(formatter);
    }

    @Override
    public String toString() {
        return "Frequency = " + this.fmtFrequency() + "\n" +
        "Interval = " + this.getInterval() + "\n" +
        "Until = " + this.fmtUntil(config.getDateFormat()) + "\n" +
        "ByDay = " + this.getByDay();
    }

    public enum Frequency {
        NONE,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }
}
