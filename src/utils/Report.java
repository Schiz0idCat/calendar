package utils;

import modules.calendar.Calendar;
import modules.calendar.Event;
import modules.people.People;
import modules.people.Person;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Report {
    public static void generate(People people, Calendar calendar) throws IOException {
        Path reportPath = Path.of("report.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(reportPath)) {
            writer.write("#=====> System Report <=====#\n\n");

            writer.write("Total people: " + people.getPeople().size() + "\n");
            writer.write("Total events: " + calendar.getAllEvents().size() + "\n");

            Map<String, Integer> eventsByPerson = new HashMap<>();
            Event maxParticipantsEvent = null;
            Event minParticipantsEvent = null;
            int totalParticipants = 0;

            for (Event e : calendar.getAllEvents()) {
                int count = e.getParticipants().size();
                totalParticipants += count;

                if (maxParticipantsEvent == null || count > maxParticipantsEvent.getParticipants().size()) {
                    maxParticipantsEvent = e;
                }
                if (minParticipantsEvent == null || count < minParticipantsEvent.getParticipants().size()) {
                    minParticipantsEvent = e;
                }

                for (Person p : e.getParticipants().values()) {
                    eventsByPerson.put(
                        p.getRut(),
                        eventsByPerson.getOrDefault(p.getRut(), 0) + 1
                    );
                }
            }

            writer.write("\nEvents per person:\n");
            for (var entry : eventsByPerson.entrySet()) {
                writer.write("- " + people.get(entry.getKey()).getName() 
                             + " (" + entry.getKey() + "): " 
                             + entry.getValue() + "\n");
            }

            writer.write("\nStatistics:\n");
            writer.write("- Average participants per event: " + 
                         (calendar.getAllEvents().isEmpty() ? 0 : (double) totalParticipants / calendar.getAllEvents().size()) + "\n");

            if (maxParticipantsEvent != null) {
                writer.write("- Event with most participants: " + maxParticipantsEvent.getTitle() +
                             " (" + maxParticipantsEvent.getParticipants().size() + " participants)\n");
            }

            if (minParticipantsEvent != null) {
                writer.write("- Event with least participants: " + minParticipantsEvent.getTitle() +
                             " (" + minParticipantsEvent.getParticipants().size() + " participants)\n");
            }
        }

        System.out.println("Report generated at: " + reportPath.toAbsolutePath());
    }
}
