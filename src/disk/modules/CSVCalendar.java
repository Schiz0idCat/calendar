package disk.modules;

import errors.modules.calendar.*;
import config.Config;
import config.modules.CalendarConfig;
import disk.FileManager;
import modules.calendar.Calendar;
import modules.calendar.Event;
import modules.people.People;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CSVCalendar extends FileManager<Calendar> {
    private static final CSVFormat READ_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader("title","date","startTime","endTime","isAllDay","location","description","participants")
            .setSkipHeaderRecord(true)
            .setIgnoreEmptyLines(true)
            .setTrim(true)
            .get();

    private static final CSVFormat WRITE_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader("title","date","startTime","endTime","isAllDay","location","description","participants")
            .setIgnoreEmptyLines(true)
            .setTrim(true)
            .get();

    private final People people;

    private static final CalendarConfig config = Config.load().getCalendar();

    public CSVCalendar(String appName, People people) throws IOException {
        super(appName, "calendar.csv", FileType.DATA);
        this.people = people;
    }

    @Override
    public void save(Calendar calendar) {
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern(config.getDateFormat());
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern(config.getTimeFormat());

        try (BufferedWriter writer = Files.newBufferedWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, WRITE_FORMAT)) {

            for (Event e : calendar.getAllEvents()) {
                // RUT de los participantes se guarda como lista separada por ;
                String participants = String.join(";", e.getParticipants().keySet());

                csvPrinter.printRecord(
                        e.getTitle(),
                        e.getDate().format(dateFmt),
                        e.getStartTime().format(timeFmt),
                        e.getEndTime().format(timeFmt),
                        e.getIsAllDay(),
                        e.getLocation(),
                        e.getDescription(),
                        participants
                );
            }

        } catch (IOException ex) {
            System.err.println("Error saving calendar data: " + ex.getMessage());
        }
    }

    @Override
    public Calendar load() {
        Calendar calendar = new Calendar();
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern(config.getDateFormat());
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern(config.getTimeFormat());

        if (!Files.exists(filePath)) return calendar;

        try (Reader reader = Files.newBufferedReader(filePath);
             CSVParser parser = CSVParser.parse(reader, READ_FORMAT)) {

            for (CSVRecord record : parser) {
                try {
                    Event e = new Event();

                    e.setTitle(record.get("title"));
                    e.setDate(LocalDate.parse(record.get("date"), dateFmt));
                    e.setStartTime(LocalTime.parse(record.get("startTime"), timeFmt));
                    e.setEndTime(LocalTime.parse(record.get("endTime"), timeFmt));
                    e.setIsAllDay(Boolean.parseBoolean(record.get("isAllDay")));
                    e.setLocation(record.get("location"));
                    e.setDescription(record.get("description"));

                    String[] participantRuts = record.get("participants").split(";");
                    for (String rut : participantRuts) {
                        if (people.contains(rut)) {
                            try {
                                e.addParticipant(people.get(rut));
                            }
                            catch (InvalidParticipantException ex) {
                                // no se avisa del error
                                // se presume que fue eliminado intencionadamente
                            }
                        }
                    }

                    // El evento se agrega si ninguna función falló
                    calendar.add(e);
                }
                catch (InvalidTitleException | InvalidDateException | InvalidTimeException ex) {
                    // Si hay error al leer un evento, no se carga en memoria
                    System.err.println("Skipping invalid event from CSV: " + ex.getMessage());
                }
            }
        }
        catch (IOException ex) {
            System.err.println("Error loading calendar data: " + ex.getMessage());
        }

        return calendar;
    }

    @Override
    public void export(Calendar calendar) throws IOException {
        Path exportPath = Path.of("calendar_export.csv"); // directorio actual de trabajo
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern(config.getDateFormat());
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern(config.getTimeFormat());

        try (BufferedWriter writer = Files.newBufferedWriter(exportPath);
        CSVPrinter csvPrinter = new CSVPrinter(writer, WRITE_FORMAT)) {

            for (Event e : calendar.getAllEvents()) {
                String participants = String.join(";", e.getParticipants().keySet());

                csvPrinter.printRecord(
                    e.getTitle(),
                    e.getDate().format(dateFmt),
                    e.getStartTime().format(timeFmt),
                    e.getEndTime().format(timeFmt),
                    e.getIsAllDay(),
                    e.getLocation(),
                    e.getDescription(),
                    participants
                );
            }

            System.out.println("Calendar exported to: " + exportPath.toAbsolutePath());
        }
    }
}
