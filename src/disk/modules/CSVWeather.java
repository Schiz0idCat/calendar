package disk.modules;

import disk.FileManager;
import modules.weather.Weather;
import modules.weather.WeatherEntry;
import config.Config;
import config.modules.CalendarConfig;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Nicolas
 */
public class CSVWeather extends FileManager<Weather> {
    private static final String[] CSV_HEADERS = {"date", "condition", "minTemp", "maxTemp", "precipitation", "windSpeed"};

    private static final CSVFormat READ_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader(CSV_HEADERS)
            .setSkipHeaderRecord(true)
            .setIgnoreEmptyLines(true)
            .setIgnoreHeaderCase(true)
            .setTrim(true)
            .get();

    private static final CSVFormat WRITE_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader(CSV_HEADERS)
            .setTrim(true)
            .get();

    private static final CalendarConfig config = Config.load().getCalendar();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(config.getDateFormat());

    public CSVWeather(String appName) throws IOException {
        super(appName, "weather.csv", FileType.DATA);
    }

    @Override
    public void save(Weather weather) {
        try (BufferedWriter writer = Files.newBufferedWriter(this.getFilePath());
             CSVPrinter csvPrinter = new CSVPrinter(writer, WRITE_FORMAT)) {

            for (WeatherEntry e : weather.getEntries()) {
                csvPrinter.printRecord(
                        e.getDate() == null ? "" : e.getDate().format(DATE_FORMATTER),
                        e.getCondition(),
                        e.getMinTemp(),
                        e.getMaxTemp(),
                        e.getPrecipitation(),
                        e.getWindSpeed()
                );
            }
        } catch (IOException e) {
            System.err.println("Error saving weather data: " + e.getMessage());
        }
    }

    @Override
    public Weather load() {
        Weather weather = new Weather();
        
        if (!Files.exists(this.getFilePath())) return weather;
        
        try (Reader reader = Files.newBufferedReader(this.getFilePath());
             CSVParser csvParser = new CSVParser(reader, READ_FORMAT)) {

            for (CSVRecord record : csvParser) {
                try {
                    WeatherEntry e = new WeatherEntry();
                    
                    String dateStr = record.isMapped("date") ? record.get("date") : "";
                    if (dateStr != null && !dateStr.isEmpty()) {
                        e.setDate(LocalDate.parse(dateStr, DATE_FORMATTER));
                    }
                    
                    e.setCondition(record.get("condition"));
                    e.setMinTemp(parseDoubleSafe(record, "minTemp"));
                    e.setMaxTemp(parseDoubleSafe(record, "maxTemp"));
                    e.setPrecipitation(parseDoubleSafe(record, "precipitation"));
                    e.setWindSpeed(parseDoubleSafe(record, "windSpeed"));
                    
                    weather.add(e);
                } catch (Exception ex) {
                    System.err.println("Skipping invalid weather record: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.err.println("Error loading weather data: " + ex.getMessage());
        }
        return weather;
    }
    
    public Weather loadFromFile(java.io.File file) throws java.io.IOException {
        Weather weather = new Weather();
        
        try (java.io.Reader reader = java.nio.file.Files.newBufferedReader(file.toPath());
             org.apache.commons.csv.CSVParser csvParser = new org.apache.commons.csv.CSVParser(reader, READ_FORMAT)) {

            for (org.apache.commons.csv.CSVRecord record : csvParser) {
                try {
                    WeatherEntry e = new WeatherEntry();
                    
                    String dateStr = record.isMapped("date") ? record.get("date") : "";
                    if (dateStr != null && !dateStr.isEmpty()) {
                        e.setDate(java.time.LocalDate.parse(dateStr, DATE_FORMATTER));
                    }
                    
                    e.setCondition(record.get("condition"));
                    e.setMinTemp(parseDoubleSafe(record, "minTemp"));
                    e.setMaxTemp(parseDoubleSafe(record, "maxTemp"));
                    e.setPrecipitation(parseDoubleSafe(record, "precipitation"));
                    e.setWindSpeed(parseDoubleSafe(record, "windSpeed"));
                    
                    weather.add(e);
                } catch (Exception ex) {
                    System.err.println("Skipping invalid weather record: " + ex.getMessage());
                }
            }
        }
        
        return weather;
    }

    private double parseDoubleSafe(CSVRecord record, String column) {
        try {
            if (!record.isMapped(column)) return 0.0;
            String s = record.get(column);
            if (s == null || s.trim().isEmpty()) return 0.0;
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException nfe) {
            System.err.println("Warning: Invalid number in column " + column + ". Value could not be read.");
            return 0.0;
        }
    }
}