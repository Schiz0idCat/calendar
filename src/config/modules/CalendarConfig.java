package config.modules;

public class CalendarConfig {
    private String dateFormat;
    private String timeFormat;

    // Getters
    public String getDateFormat() {
        return this.dateFormat;
    }

    public String getTimeFormat() {
        return this.timeFormat;
    }

    // Setters
    // TODO: Hacer que esto modifique settings.toml
    // TODO: Añadir validaciones para corroborar que sea un formato admitido
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    // TODO: Hacer que esto modifique settings.toml
    // TODO: Añadir validaciones para corroborar que sea un formato admitido
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    // Setters to default
    // TODO: Hacer que esto modifique settings.toml
    public void setDateFormat() {
        this.dateFormat = "dd-MM-yyyy";
    }

    // TODO: Hacer que esto modifique settings.toml
    public void setTimeFormat() {
        this.timeFormat = "HH:mm";
    }

    // Formatter
    @Override
    public String toString() {
        return "[calendar]" + "\n" +
        "dateFormat = \"" + this.getDateFormat() + "\"" + "\n" +
        "timeFormat = \"" + this.getTimeFormat() + "\"";
    }
}
