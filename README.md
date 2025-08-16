# **Informe de Requerimientos del Sistema**
**Proyecto:** Calendar.

**Descripción:** Aplicación para gestionar eventos y reuniones.

**Autores:**
- Agustín Guzmán.
- Nicolás Leiva.
- Felipe Marques.

---

## **1 Introducción**
Calendar es una applicación para gestionar tu agenda. Pensada para ordenar tu vida.

---

### **2 Alcance**
La aplicación permite:
- Programar, modificar y eliminar reuniones/eventos.
- Comunicarse con la aplicación mediante interfaz de terminal.

---

## **3 Requerimientos Funcionales**

### **3.1 Gestión de Calendario**
- [ ] **RF-00:** Crear eventos.
- [ ] **RF-01:** Modificar un evento existente.
- [ ] **RF-02:** Eliminar un evento.
- [ ] **RF-03:** Buscar y filtrar evento por sus campos.
- [ ] **RF-04:** Todo esto debe ser gestionado con un archivo .*{inserte extesión}*. (solo si hay tiempo).

#### **3.1.1 Clase Recurrence**
- [x] **RF-05:** La recurrencia está definida por:
    - [x] Frecuencia (obligatorio) -> Frequency
    - [x] Intervalo (obligatorio) -> int
    - [x] Fecha de termino (obligatorio) -> LocalDate
    - [x] Dias de la semana (obligatorio) -> Set<DayOfWeek>
- [x] **RF-06:** Debe haber un constructor que respete lo anterior.
- [x] **RF-07:** Todos los campos deben ser privados.
- [x] **RF-08:** Todos los campos deben ser accedidos por getters
- [x] **RF-09:** Todos los campos deben ser modificados con setters.
- [x] **RF-10:** Todos los campos deben poder ser printeables.
- [x] **RF-11:** La clase debe poder ser printeable.

#### **3.1.2 Clase Event**
- [x] **RF-12:** El evento está definido por:
    - [x] Título (obligatorio) -> String
    - [x] Fecha (obligatorio) -> LocalDate
    - [x] Hora de Inicio (obligatorio) -> LocalTime
    - [x] Hora de Termino (obligatorio) -> LocalTime
    - [x] Si dura todo el día (obligatorio) -> Boolean
    - [x] Recurrencia (obligatorio) -> Recurrence
    - [x] Ubicación (opcional) -> String
    - [x] Descripción (opcional) -> String
- [ ] **RF-13:** Debe haber un constructor que respete lo anterior.
- [x] **RF-14:** Todos los campos deben ser privados.
- [x] **RF-15:** Todos los campos deben ser accedidos por getters
- [ ] **RF-16:** Todos los campos deben ser modificados con setters.
- [x] **RF-17:** Todos los campos deben poder ser printeables.
- [x] **RF-18:** La clase debe poder ser printeable.

#### **3.1.3 Clase Calendar**
- [ ] **RF-19:** Se debe usar *{inserte estructura de datos escogida}* para gestionar los eventos.
- [ ] **RF-20:** Se debe poder ingresar un nuevo evento.
- [ ] **RF-21:** Se debe poder eliminar un evento (esto implica eliminarlo de la memoria).
- [ ] **RF-22:** Se debe poder filtrar un evento según el título (retornar todas las coincidencias).
- [ ] **RF-23:** Se debe poder filtrar un evento según la fecha (retornar todas las coincidencias).

### **3.2 Interfaz Gráfica**
- [x] **RF-24:** Debe permitir mostrar CalendarUI.
- [x] **RF-25:** Debe permitir salir de la interfaz.

#### **3.2.1 Clase CalendarUI**
- [ ] **RF-26:** Debe permitir listar todos los eventos.
- [ ] **RF-27:** Debe permitir mostrar un solo evento.
- [ ] **RF-28:** Debe permitir añadir un evento.
- [ ] **RF-29:** Debe permitir modificar los campos de un evento existente.
- [ ] **RF-30:** Debe permitir eliminar un evento (esto lo borraría de la memoria).
- [x] **RF-31:** Debe permitir salir de la interfaz.
