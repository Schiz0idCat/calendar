/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.GUI;

import modules.weather.Weather;
import modules.weather.WeatherEntry;
import disk.modules.CSVWeather;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Nicolas
 */
public class WeatherPanel extends JPanel {
    private Weather weather;
    private CSVWeather csvWeather;
    private JPanel cardsContainer;

    public WeatherPanel() {
        super(new BorderLayout());
        
        cardsContainer = new JPanel(new GridLayout(0, 3, 10, 10));
        cardsContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(new JScrollPane(cardsContainer), BorderLayout.CENTER);
    }
    
    public void setData(Weather weather, CSVWeather csvWeather) {
        this.weather = weather;
        this.csvWeather = csvWeather;
        cargarDatos();
    }

    private void cargarDatos() {
        if (weather == null) return;

        cardsContainer.removeAll();

        for (WeatherEntry entry : weather.getEntries()) {
            WeatherCardPanel card = new WeatherCardPanel(entry);
            cardsContainer.add(card);
        }

        cardsContainer.revalidate();
        cardsContainer.repaint();
    }

    public void saveWeather() {
        try {
            if (csvWeather != null && weather != null) {
                csvWeather.save(weather);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving weather data: " + e.getMessage(),
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
