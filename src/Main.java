import ui.UI;
import modules.calendar.Calendar;
import modules.people.People;
import modules.calendar.Event;
import modules.people.Person;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        final Calendar calendar = new Calendar();
        final People people = new People();

        Person alice = new Person();
        alice.setRut("11.111.111-1");
        alice.setName("Alice Smith");
        alice.setEmail("alice@example.com");
        alice.setPhone(123456789);

        Person bob = new Person();
        bob.setRut("22.222.222-2");
        bob.setName("Bob Johnson");
        bob.setEmail("bob@example.com");
        bob.setPhone(987654321);

        people.add(alice);
        people.add(bob);

        Event meeting = new Event();
        meeting.setTitle("Team Meeting");
        meeting.setDate(LocalDate.now().plusDays(1));
        meeting.setStartTime(LocalTime.of(10, 0));
        meeting.setEndTime(LocalTime.of(11, 0));
        meeting.setLocation("Conference Room");
        meeting.setDescription("Monthly team meeting to review progress");
        meeting.addParticipant(alice);
        meeting.addParticipant(bob);

        Event holiday = new Event();
        holiday.setTitle("Public Holiday");
        holiday.setDate(LocalDate.now().plusDays(2));
        holiday.setIsAllDay(true);
        holiday.setDescription("National holiday - no work");

        Event birthday = new Event();
        birthday.setTitle("Alice's Birthday");
        birthday.setDate(LocalDate.now().plusDays(5));
        birthday.setStartTime(LocalTime.of(18, 0));
        birthday.setEndTime(LocalTime.of(21, 0));
        birthday.setLocation("Alice's House");
        birthday.addParticipant(alice);

        calendar.add(meeting);
        calendar.add(holiday);
        calendar.add(birthday);

        UI.run(calendar, people);
    }
}
