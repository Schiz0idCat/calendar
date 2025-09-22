El proyecto se centra en la creación de un calendario para el fácil manejo de los quehaceres diarios.

Esto se logra haciendo concretos dos conceptos abstractos: El evento y el calendario. El evento es el hecho puntual que le interesa al usuario recordar (inspirado en el estándar RFC-5545), mientras que el calendario colecciona y gestiona dichos eventos.

* [Instalación](#Instalación)
    * [Instrucciones](#Instrucciones)
    * [Dependencias](#Dependencias)
    * [Compilación](#Compilación)
    * [Ejecución](#Ejecución)
* [Configuración](#Configuración)
    * [Calendario](#Calendario)
* [Uso](#Uso)
    * [Interfaz](#Interfaz)
        * [TUI](#TUI)
        * [GUI](#GUI)
    * [Manejo de Archivos](#Manejo-de-Archivos)
* [Créditos](#Créditos) 

# Instalación
## Instrucciones
```
git clone https://www.github.com/schiz0idcat/calendar.git calendar
cd calendar
```

## Dependencias
- openJDK 11
- Apache Maven 3.9.11

## Compilación
```
mvn clean compile exec:java # ejecutar dentro de Maven
mvn compile # solo compilar
```

## Ejecución
```
mvn clean package # obtener el .jar
java -jar target/calendar-1.5.0.jar # ejecutar el .jar
```

# Configuración
La configuración se almacena en [settings.toml](./resources/settings.toml).

## Calendario
Configuración de calendario.

```toml
[calendar]
dateFormat="dd-MM-yyyy" # Formato de la fecha (día-mes-año)
timeFormat="HH:mm" # Formato de la hora (hora:minutos)
```

# Uso
## Interfaz
### TUI
Una de las alternativas para interactuar con nuestro software es la Terminal User Interface.
Minimalista y directa.

### GUI
Además, está la Graphical User Interface para una experiencia más amigable a la vista.

## Manejo de Archivos
Dado que la app respeta la [XDG Base Directory Specification](https://specifications.freedesktop.org/basedir-spec/latest/), los archivos generados por esta se guardan en diferentes directorios dependiendo del sistema.

La base de datos no relacional para los eventos del calendario y las personas registradas en el sistema se guardan en:

```
On Mac OS X : /Users/<Account>/Library/Application Support/<AppName>
On Windows XP : C:\Documents and Settings\<Account>\Application Data\Local Settings\<AppAuthor>\<AppName>
On Windows vista or later : C:\Users\<Account>\AppData\<AppAuthor>\<AppName>
On Unix/Linux : /home/<account>/.local/share/<AppName>
```

Para más detalle revisar la dependencia usada [acá](https://github.com/harawata/appdirs).

# Créditos
Este proyecto fue realizado por:
- Agustín Guzmán [[GitHub](https://github.com/Schiz0idCat)]
- Nicolás Leiva [[GitHub](https://github.com/nico0417)]
- Felipe Márquez [[GitHub](https://github.com/fmarquezmu)]
