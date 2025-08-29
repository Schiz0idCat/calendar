# Avance
## SIA1.1 Análisis de los  datos y principales funcionalidades
El proyecto se centra en la creación de un calendario para el fácil manejo de los quehaceres diarios.

Esto se logra haciendo concretos dos conceptos abstractos: El evento y el calendario. El evento es el hecho puntual que le interesa al usuario recordar, mientras que el calendario colecciona y gestiona dichos eventos.

## SIA1.2 Diseño conceptual de clases del Dominio y su código en java
Para lograr lo anterior se crearon las siguientes clases.

### Módulos principales del proyecto
#### Calendar
##### Clase Main
Main hace tan solo 2 cosas:
1. Inicializar el calendario.
2. Correr la interfaz de terminal para la gestión del calendario.

##### Clase Calendar
Calendar es capaz de gestionar mediente una lista los eventos mediante operaciones CRUD.

Contiene:
- events (List<Event>) -> Recopilación de todos los eventos

##### Clase Event
Event es capaz de contener los datos relevantes de un evento

tales son:
- id (int)                -> Identificador único
- title (String)          -> Título
- date (LocalDate)        -> Fecha
- startTime (LocalTime)   -> Hora de inicio
- endTime (LocalTime)     -> Hora de termino
- isAllDay (boolean)      -> Indicador de si el evento dura todo el día
- recurrence (Recurrence) -> Las repeticiones del evento
- location (String)       -> Lugar del evento
- description (String)    -> Detalles del evento


add(Person person) -> void:
get(String rut) -> Person:
remove(String rut) -> void:
contains(String rut) -> boolean:
isEmpty() -> boolean:

### Clases auxiliares
#### UI
##### Clase UI
UI gestiona todos los módulos del proyecto (tan solo Calendar, hasta este momento). UI, recolecta los UI específicos de los módulos (tales como CalendarUI), para Exponer los módulos principales del proyecto.

##### Módulos
###### Clase CalendarUI
CalendarUI expone la API de Calendar para poder manipular la gestión de eventos mediente las operaciones CRUD

#### Configuraciones
##### Clase Config
Config recolecta las configuraciones pertinentes para cada módulo, registradas en un archivo .toml común

##### Módulos
###### Clase CalendarConfig
CalendarConfig recopila todas las configuracines propias del calendario.

Estas son:
- dateFormat (String) -> Formato de fecha
- timeFormat (String) -> Formato de hora
- weekStart (String)  -> Día con el que empieza la semana

## SIA1.3 Todos los atributos de Todas las clases deben ser privados y poseer sus respectivos Setters y Getters
Esto se logra en todas las clases en los que hya campos declarados y sea coherente que los tengan

ruta: src/modules/calendar/Calendar.java
lineas:

```java
public List<Event> getAllEvents() {
    return new ArrayList<>(events); 
}
```

ruta: src/modules/calendar/Event.java
lineas:

```java
// Getters
public int getId() {
    return this.id;
}

public String getTitle() {
    return this.title;
}

public LocalDate getDate() {
    return this.date;
}

public LocalTime getStartTime() {
    return this.startTime;
}

public LocalTime getEndTime() {
    return this.endTime;
}

public boolean getIsAllDay() {
    return this.isAllDay;
}

public Recurrence getRecurrence() {
    return this.recurrence;
}

public String getLocation() {
    return this.location;
}

public String getDescription() {
    return this.description;
}

// Setters
public void setTitle(String title) {
    if (title == null || title.trim().isEmpty()){
        throw new IllegalArgumentException("Title cannot be null or empty");
    }
    this.title = title.trim();
}

public void setTitle() {
    this.title = "default title";
}

public void setDate(LocalDate date) {
    if(date == null) {
        throw new IllegalArgumentException("Date cannot be null or empty");
    }
    this.date = date;
}

public void setDate() {
    this.date = LocalDate.now();
}

public void setStartTime(LocalTime startTime) {
    if(startTime == null) {
        throw new IllegalArgumentException("Start time cannot be null for non-all-day events");
    }
    if (this.endTime != null && startTime.isAfter(this.endTime)) {
        throw new IllegalArgumentException("Start time cannot be after end time");
    }
    if(startTime == LocalTime.MIN && endTime == END_OF_DAY){
        this.isAllDay = true;
    }
    else {
        this.isAllDay = false;
    }
    this.startTime = startTime;
}

public void setEndTime(LocalTime endTime) {
    if(endTime == null) {
        throw new IllegalArgumentException("End time cannot be null for non-all-day events");
    }
    if (this.startTime != null && endTime.isBefore(this.startTime)) {
        throw new IllegalArgumentException("End time cannot be before start time");
    }
    if(endTime == END_OF_DAY && startTime == LocalTime.MIN){
        this.isAllDay = true;
    }
    else {
        this.isAllDay = false;
    }
    this.endTime = endTime;
}

public void setIsAllDay(boolean isAllDay) {
    this.isAllDay = isAllDay;
    if (isAllDay) {
        this.startTime = LocalTime.MIN;
        this.endTime = END_OF_DAY;
    }
}

public void setIsAllDay() {
    this.isAllDay = true;
    this.startTime = LocalTime.MIN;
    this.endTime = END_OF_DAY;
}

public void setRecurrence(Recurrence recurrence) {
    if (recurrence == null) {
        throw new IllegalArgumentException("Reccurrence cannot be null");
    }
    this.recurrence = recurrence;
}

public void setLocation(String location) {
    if (location == null){
        this.location = "";
        return;
    }
    this.location = location.trim();
}

public void setLocation() {
    this.location = "";
}

public void setDescription(String description) {
    if (description == null){
        this.description = "";
        return;
    }
    this.description = description.trim();
} 

public void setDescription() {
        this.description = "";
} 
```

ruta: src/config/Config.java
lineas:

```java
public CalendarConfig getCalendar() {
    return calendar;
}
```

ruta: src/config/CalendarConfig.java
lineas:

```java
// Getters
public String getDateFormat() {
    return this.dateFormat;
}

public String getTimeFormat() {
    return this.timeFormat;
}

public String getWeekStart() {
    return this.weekStart;
}

// Setters
public void setDateFormat(String dateFormat) {
    this.dateFormat = dateFormat;
}

public void setTimeFormat(String timeFormat) {
    this.timeFormat = timeFormat;
}

public void setWeekStart(String weekStart) {
    this.weekStart = weekStart;
}

// Setters to default
public void setDateFormat() {
    this.dateFormat = "dd-MM-yyyy";
}

public void setTimeFormat() {
    this.timeFormat = "HH:mm";
}

public void setWeekStart() {
    this.weekStart = "MONDAY";
}
```

## SIA1.4 Se deben incluir datos iniciales dentro del código
No implementado

## SIA1.5 Diseño conceptual y codificación de 2 colecciones de objetos, con la 2ª colección anidada

## SIA1.6 Diseño conceptual y codificación de 2 clases que utilicen sobrecarga de métodos
Las siguientes clases contienen sobrecarga de métodos.

Ruta: src/modules/calendar/Event.java
Lineas:
Diseño:

```java
public void setTitle(String title) {
    if (title == null || title.trim().isEmpty()){
        throw new IllegalArgumentException("Title cannot be null or empty");
    }
    this.title = title.trim();
}

public void setTitle() {
    this.title = "default title";
}

public void setDate(LocalDate date) {
    if(date == null) {
        throw new IllegalArgumentException("Date cannot be null or empty");
    }
    this.date = date;
}

public void setDate() {
    this.date = LocalDate.now();
}

public void setIsAllDay(boolean isAllDay) {
    this.isAllDay = isAllDay;
    if (isAllDay) {
        this.startTime = LocalTime.MIN;
        this.endTime = END_OF_DAY;
    }
}

public void setIsAllDay() {
    this.isAllDay = true;
    this.startTime = LocalTime.MIN;
    this.endTime = END_OF_DAY;
}

public void setLocation(String location) {
    if (location == null){
        this.location = "";
        return;
    }
    this.location = location.trim();
}

public void setLocation() {
    this.location = "";
}

public void setDescription(String description) {
    if (description == null){
        this.description = "";
        return;
    }
    this.description = description.trim();
} 

public void setDescription() {
        this.description = "";
} 
```

Ruta: src/config/modules/CalendarConfig.java
Lineas:
Diseño:

```java
// Setters
public void setDateFormat(String dateFormat) {
    this.dateFormat = dateFormat;
}

public void setTimeFormat(String timeFormat) {
    this.timeFormat = timeFormat;
}

public void setWeekStart(String weekStart) {
    this.weekStart = weekStart;
}

// Setters to default
public void setDateFormat() {
    this.dateFormat = "dd-MM-yyyy";
}

public void setTimeFormat() {
    this.timeFormat = "HH:mm";
}

public void setWeekStart() {
    this.weekStart = "MONDAY";
}
```

## SIA1.7 Diseño conceptual y codificación de al menos 1 clase mapa del Java Collections Framework
No realizado

## SIA1.8 Se debe hacer un menú para el Sistema donde ofrezcan funcionalidades de Interacción
Se debe implementar:
1. Inserción Manual / agregar elemento
Esto se logra en:
Ruta:
Lineas:

2. Mostrar por pantalla listado de elementos. Esto para la 2ª colección de objetos (colección anidada) del SIA1.5

## SIA1.9 Todas las funcionalidades deben ser implementadas mediante consola

## SIA1.10 Utilización de GitHub

# Final
## SIA2.1 Diseño de diagrama de clases UML

## SIA2.2 Persistencia de datos

## SIA2.3 La implementación de todas las interfaces gráficas (ventanas) para interactuar con el usuario, considerando componentes SWING.

## SIA2.4 Se debe hacer un menú para el Sistema donde ofrezcan funcionalidades de interacción
Se debe implementar:
1. Edición o modificación del elemento
2. Eliminación del elemento. Esto para la 2ª colección de objetos (colección anidada) del SIA1.5

## SIA2.5 Se deben incluir al menos 1 funcionalidad propia que sean de utilidad para el negocio

## SIA2.6 El código fuente debe estar bien modularizado y seguir buenas prácticas

## SIA2.7 Diseño y codificación de 2 (dos) clases que utilicen sobreescritura de métodos

## SIA2.8 Implementar el manejo de excepciones capturando errores potenciales específicos mediante Try-catch

## SIA2.9 Crear 2 clases que extiendan de una Excepción y que se utilicen en el programa

## SIA2.10 Se debe generar un reporte en archivo txt que considere mostrar datos de la colección de objetos (ej: csv)

## SIA2.11 Continuidad en la utilización de GitHub (Realización de al menos 3 Commit adicionales a los ya hechos en el avance)

## SIA2.12 Implementar las funcionalidades de agregar, eliminar o modificar en elementos de la 1ra colección o nivel

