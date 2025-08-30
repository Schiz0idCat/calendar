Colaboradores:
- Agustín Guzmán
- Nicolás Leiva
- Felipe Márquez

El proyecto se centra en la creación de un calendario para el fácil manejo de los quehaceres diarios.

Esto se logra haciendo concretos dos conceptos abstractos: El evento y el calendario. El evento es el hecho puntual que le interesa al usuario recordar (inspirado en el estándar RFC-5545), mientras que el calendario colecciona y gestiona dichos eventos.

* [Instalación](#Instalación)
    * [Instrucciones](#Instrucciones)
    * [Dependencias](#Dependencias)
    * [Compilación](#Compilación)
    * [Ejecutar](#Ejecutar)
* [Configuración](#Configuración)
    * [Calendario](#Calendario)

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

## Ejecutar
```
mvn clean package # obtener el .jar
java -jar target/calendar-1.5.0.jar # ejecutar el .jar
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
