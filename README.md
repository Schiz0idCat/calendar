El proyecto se centra en la creación de un calendario para el fácil manejo de los quehaceres diarios.

Esto se logra haciendo concretos dos conceptos abstractos: El evento y el calendario. El evento es el hecho puntual que le interesa al usuario recordar (inspirado en el estándar RFC-5545), mientras que el calendario colecciona y gestiona dichos eventos.

* [Instalación](#Instalación)
    * [Descarga](#Descarga)
    * [Dependencias](#Dependencias)
    * [Compilación](#Compilación)
    * [Ejecución](#Ejecución)
* [Configuración](#Configuración)
    * [Calendario](#Calendario)
* [Uso](#Uso)
    * [Instrucciones](#Instrucciones)
        * [Ejemplos](#Ejemplos)
    * [Interfaz](#Interfaz)
        * [TUI](#TUI)
        * [GUI](#GUI)
    * [Manejo de Archivos](#Manejo-de-Archivos)
* [Créditos](#Créditos) 

# Instalación
## Descarga
```
git clone https://www.github.com/schiz0idcat/calendar.git calendar
cd calendar
```

## Dependencias
- openJDK 11
- Apache Maven 3.9.11

## Compilación
```
mvn compile # solo compilar
mvn clean compile exec:java # ejecutar dentro de maven (corre la gui por defecto)
mvn exec:java -Dexec.mainClass="Main.java" -Dexec.args="{ARGUMENT}" # ejecutar dentro de maven especificando el argumento
```

## Ejecución
```
mvn clean package # obtener el .jar
java -jar target/calendar-1.5.0.jar {ARGUMENTO} # ejecutar el .jar
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
## Instrucciones
El flujo inicial del programa lo administra la CLI.
Esta recive 3 argumentos válidos:
- *nada*: si no se le pasa ningún argumento, el programa ejecuta la GUI.
- gui: ejecuta la GUI.
- tui: ejecuta la TUI.

En caso de pasar un argumento inválido, el programa imprime una alerta por consola.

### Ejemplos
Acá algunos ejemplos de las formas de ejecutar el programa.

```
#=====> DESDE MAVEN <=====#
# GUI
mvn exec:java
mvn exec:java -Dexec.mainClass="Main.java"
mvn exec:java -Dexec.mainClass="Main.java" -Dexec.args=""
mvn exec:java -Dexec.mainClass="Main.java" -Dexec.args="gui"

# TUI
mvn exec:java -Dexec.mainClass="Main.java" -Dexec.args="tui"

#=====> DESDE JAVA <=====#
# GUI
java -jar target/calendar-1.5.0.jar
java -jar target/calendar-1.5.0.jar gui

# TUI
java -jar target/calendar-1.5.0.jar tui
```

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
