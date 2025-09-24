package disk.modules;

import errors.modules.people.*;
import disk.FileManager;
import modules.people.People;
import modules.people.Person;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;

public class CSVPeople extends FileManager<People> {
    private static final CSVFormat READ_FORMAT = CSVFormat.DEFAULT.builder()
    .setHeader("rut", "name", "email", "phone")
    .setSkipHeaderRecord(true)
    .setIgnoreEmptyLines(true)
    .setIgnoreHeaderCase(true)
    .setTrim(true)
    .get();

    private static final CSVFormat WRITE_FORMAT = CSVFormat.DEFAULT.builder()
    .setHeader("rut", "name", "email", "phone")
    .setIgnoreEmptyLines(true)
    .setTrim(true)
    .get();

    public CSVPeople(String appName) throws IOException {
        super(appName, "people.csv", FileType.DATA);
    }

    @Override
    public void save(People people) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, WRITE_FORMAT)) {

            for (Person p : people.getPeople().values()) {
                csvPrinter.printRecord(p.getRut(), p.getName(), p.getEmail(), p.getPhone());
            }

        }
        catch (IOException e) {
            System.err.println("Error saving people data: " + e.getMessage());
        }
    }

    @Override
    public People load() {
        People people = new People();

        if (!Files.exists(filePath)) return people;

        try (Reader reader = Files.newBufferedReader(filePath);
             CSVParser csvParser = CSVParser.parse(reader, READ_FORMAT)) {

            for (CSVRecord record : csvParser) {
                try {
                    Person p = new Person();
                    p.setRut(record.get("rut"));
                    p.setName(record.get("name"));
                    p.setEmail(record.get("email"));
                    p.setPhone(record.get("phone"));
                    people.add(p);
                } catch (InvalidRutException | InvalidNameException | InvalidEmailException e) {
                    System.err.println("A person couldn't be read");
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error loading people data: " + e.getMessage());
        }

        return people;
    }
}
