/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules.weather;
import java.time.LocalDate;
/**
 *
 * @author Nicolas
 */
public class WeatherEntry {
    private LocalDate date;
    private String condition;
    private double minTemp;
    private double maxTemp;
    private double precipitation; 
    private double windSpeed; 

    public WeatherEntry() {}

    public WeatherEntry(LocalDate date, String condition, double minTemp, double maxTemp, double precipitation, double windSpeed) {
        this.date = date;
        this.condition = condition;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
    }
    
    //getters
    public LocalDate getDate() { 
        return this.date; 
    }
    public String getCondition() { 
        return this.condition; 
    }
    
    public double getMinTemp() { 
        return this.minTemp; 
    }
    
    public double getMaxTemp() { 
        return this.maxTemp; 
    }
    
    public double getPrecipitation() { 
        return this.precipitation; 
    }
    
    public double getWindSpeed() { 
        return this.windSpeed; 
    }
    
    //setters
    public void setDate(LocalDate date) {
        this.date = date; 
    }

    public void setCondition(String condition) {
        this.condition = condition; 
    }

    public void setMinTemp(double minTemp) { 
        this.minTemp = minTemp; 
    }

    
    public void setMaxTemp(double maxTemp) { 
        this.maxTemp = maxTemp; 
    }

    
    public void setPrecipitation(double precipitation) { 
        this.precipitation = precipitation; 
    }

    
    public void setWindSpeed(double windSpeed) { 
        this.windSpeed = windSpeed; 
    }
}
