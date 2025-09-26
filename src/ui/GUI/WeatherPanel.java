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
    private JTable weatherTable;
    private DefaultTableModel tableModel;

    // Estos objetos ahora serán "inyectados" desde CalendarMainForm
    private Weather weather;
    private CSVWeather csvWeather;

    public WeatherPanel() {
        super(new BorderLayout());

        // Inicializar componentes de la UI
        String[] columnNames = {"Fecha", "Condición", "Mínima (°C)", "Máxima (°C)", "Precipitación (mm)", "Viento (km/h)"};
        tableModel = new DefaultTableModel(columnNames, 0);
        weatherTable = new JTable(tableModel);

        add(new JScrollPane(weatherTable), BorderLayout.CENTER);
    }

    /**
     * NUEVO MÉTODO: Inyecta los datos compartidos desde CalendarMainForm.
     * Este es el método clave que faltaba.
     */
    public void setData(Weather weather, CSVWeather csvWeather) {
        this.weather = weather;
        this.csvWeather = csvWeather;
        // Una vez que tenemos los datos, cargamos la tabla.
        cargarDatos();
    }

    /**
     * El método cargarDatos ahora usa la variable de clase 'weather'.
     */
    private void cargarDatos() {
        if (weather == null) {
            return; // No hacer nada si los datos no han sido inyectados
        }
        tableModel.setRowCount(0); // Limpiar tabla
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (WeatherEntry e : weather.getEntries()) {
            tableModel.addRow(new Object[]{
                    e.getDate() == null ? "" : e.getDate().format(fmt),
                    e.getCondition(),
                    e.getMinTemp(),
                    e.getMaxTemp(),
                    e.getPrecipitation(),
                    e.getWindSpeed()
            });
        }
    }

    /**
     * NUEVO MÉTODO: Permite que CalendarMainForm guarde los datos del clima.
     * Este era el otro método que faltaba.
     */
    public void saveWeather() {
        try {
            if (csvWeather != null && weather != null) {
                csvWeather.save(weather);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error guardando los datos del clima: " + e.getMessage(),
                    "Error de Guardado", JOptionPane.ERROR_MESSAGE);
        }
    }
}
