* [Instalación](#Instalación)
    * [Instrucciones](#Instrucciones)
    * [Dependencias](#Dependencias)
    * [Compilación](#Compilación)
* [CONFIGURATION](#configuration)
    * [Calendario](#Calendario)

# Instalación
## Instrucciones
```
git clone https://www.schiz0idcat/calendar.git calendar
cd calendar
```

## Dependencias
- openJDK 11
- Apache Maven 3.9.11

## Compilación
```
mvn clean compile exec:java
```

# Configuración
La configuración se almacena en [settings.toml](./resources/settings.toml)

## Calendario
Configuración de calendario

```toml
[calendar]
dateFormat="dd-MM-yyyy" # Formato de la fecha (día-mes-año)
timeFormat="HH:mm" # Formato de la hora (hora:minutos)
```
