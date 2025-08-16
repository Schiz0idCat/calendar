# **Informe de Requerimientos del Sistema**
**Proyecto:** Calendar.
**Descripción:** Aplicación para gestionar eventos y reuniones.
**Autores:**
- Agustín Guzmán.
- Nicolás Leiva.
- Felipe Marques.

---

## **1 Introducción**
Life Organizer es una applicación para gestionar tu agenda. Pensada para ordenar tu vida.

---

### **2 Alcance**
La aplicación permite:
- Programar, modificar y eliminar reuniones/eventos.
- Comunicarse con la aplicación mediante interfaz de terminal.

---

## **3 Requerimientos Funcionales**

### **3.1 Gestión de Calendario**
- [ ] **RF-16:** Crear eventos.
- [ ] **RF-17:** Modificar un evento existente.
- [ ] **RF-18:** Eliminar un evento.
- [ ] **RF-19:** Buscar y filtrar evento por sus campos.
- [ ] **RF-19:** Todo esto debe ser gestionado con un archivo .csv.

#### **3.1.1 Clase Recurrence**
- [ ] **RF-00:** La recurrencia está definida por:
    - [ ] Frecuencia (obligatorio) -> Frequency
    - [ ] Intervalo (obligatorio) -> int
    - [ ] Fecha de termino (obligatorio) -> LocalDate
    - [ ] Dias de la semana (obligatorio) -> Set<DayOfWeek>
- [ ] **RF-21:** Debe haber un constructor que respete lo anterior.
- [x] **RF-22:** Todos los campos deben ser privados.
- [x] **RF-23:** Todos los campos deben ser accedidos por getters
- [ ] **RF-24:** Todos los campos deben ser modificados con setters.
- [x] **RF-25:** Todos los campos deben poder ser printeables.
- [x] **RF-26:** La clase debe poder ser printeable.

#### **3.1.2 Clase Event**
- [ ] **RF-20:** El evento está definido por:
    - Título (obligatorio) -> String
    - Fecha (obligatorio) -> LocalDate
    - Hora de Inicio (obligatorio) -> LocalTime
    - Hora de Termino (obligatorio) -> LocalTime
    - Si dura todo el día (obligatorio) -> Boolean
    - Recurrencia (obligatorio) -> Recurrence
    - Ubicación (opcional) -> String
    - Descripción (opcional) -> String
- [ ] **RF-21:** Debe haber un constructor que respete lo anterior.
- [x] **RF-22:** Todos los campos deben ser privados.
- [x] **RF-23:** Todos los campos deben ser accedidos por getters
- [ ] **RF-24:** Todos los campos deben ser modificados con setters.
- [x] **RF-25:** Todos los campos deben poder ser printeables.
- [x] **RF-26:** La clase debe poder ser printeable.

#### **3.1.3 Clase Calendar**
- [ ] **RF-27:** Se debe usar *{inserte estructura de datos escogida}* para gestionar los eventos.
- [ ] **RF-28:** Se debe poder ingresar un nuevo evento.
- [ ] **RF-29:** Se debe poder eliminar un evento (esto implica eliminarlo de la memoria).
- [ ] **RF-30:** Se debe poder filtrar un evento según el título (retornar todas las coincidencias).
- [ ] **RF-31:** Se debe poder filtrar un evento según la fecha (retornar todas las coincidencias).

### **3.2 Interfaz Gráfica**
- [ ] **RF-32:** Debe permitir mostrar AddressBookUI.
- [ ] **RF-33:** Debe permitir mostrar CalendarUI.
- [ ] **RF-34:** Debe permitir salir de la interfaz.

#### **3.2.1 Clase CalendarUI**
- [ ] **RF-41:** Debe permitir listar todos los eventos.
- [ ] **RF-42:** Debe permitir mostrar un solo evento.
- [ ] **RF-43:** Debe permitir añadir un evento.
- [ ] **RF-44:** Debe permitir modificar los campos de un evento existente.
- [ ] **RF-45:** Debe permitir eliminar un evento (esto lo borraría de la memoria).
- [ ] **RF-46:** Debe permitir salir de la interfaz.
