package modules.calendar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.Set;

class RecurrenceTest {
    @Test
    void testConstructorAndGetters() {
        LocalDate until = LocalDate.of(2025, 8, 16);
        Set<DayOfWeek> days = Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);

        Recurrence recurrence = new Recurrence(Recurrence.Frequency.WEEKLY, 2, until, days);

        assertEquals(Recurrence.Frequency.WEEKLY, recurrence.getFrequency());
        assertEquals(2, recurrence.getInterval());
        assertEquals(until, recurrence.getUntil());
        assertEquals(days, recurrence.getByDay());
    }

    @Test
    void testSetters() {
        Recurrence recurrence = new Recurrence(Recurrence.Frequency.NONE, 0, LocalDate.now(), Set.of());

        LocalDate newUntil = LocalDate.of(2030, 1, 1);
        recurrence.setFrequency(Recurrence.Frequency.MONTHLY);
        recurrence.setInterval(3);
        recurrence.setUntil(newUntil);
        recurrence.setByDay(Set.of(DayOfWeek.WEDNESDAY));

        assertEquals(Recurrence.Frequency.MONTHLY, recurrence.getFrequency());
        assertEquals(3, recurrence.getInterval());
        assertEquals(newUntil, recurrence.getUntil());
        assertEquals(Set.of(DayOfWeek.WEDNESDAY), recurrence.getByDay());
    }

    @Test
    void testToString() {
        LocalDate until = LocalDate.of(2025, 8, 16);
        Set<DayOfWeek> days = Set.of(DayOfWeek.MONDAY);

        Recurrence recurrence = new Recurrence(Recurrence.Frequency.DAILY, 1, until, days);

        String result = recurrence.toString();
        assertTrue(result.contains("Frequency = daily"));
        assertTrue(result.contains("Interval = 1"));
        assertTrue(result.contains("Until = 16-08-2025"));
        assertTrue(result.contains("ByDay = [MONDAY]"));

        for (DayOfWeek day : days) {
            assertTrue(result.contains(day.toString()));
        }
    }
}
