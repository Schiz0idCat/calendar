/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules.weather;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public class Weather {
    private final List<WeatherEntry> entries;

    public Weather() {
        this.entries = new ArrayList<>();
    }

    public void add(WeatherEntry entry) {
        if (entry != null) this.entries.add(entry);
    }

    public List<WeatherEntry> getEntries() {
        return Collections.unmodifiableList(this.entries);
    }

    public boolean isEmpty() {
        return this.entries.isEmpty();
    }

    public WeatherEntry getByDate(LocalDate date) {
        for (WeatherEntry e : this.entries) {
            if (e.getDate() != null && e.getDate().equals(date)) return e;
        }
        return null;
    }

    public void clear() {
        this.entries.clear();
    }

    @Override
    public String toString() {
        if (this.entries.isEmpty()) return "No weather data";
        StringBuilder sb = new StringBuilder();
        for (WeatherEntry e : this.entries) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}