# **Informe de Requerimientos del Sistema**
**Proyecto:** Life Organizer.
**Descripción:** Aplicación para gestionar eventos y contactos.
**Autores:**
- Agustín Guzmán.
- Nicolás Leiva.
- Felipe Marques.

---

## **1 Introducción**
Life Organizer es una applicación para gestionar contactos y agenda. Pensada para ordenar tu vida.

---

### **2 Alcance**
La aplicación permite:
- Almacenar y gestionar contactos con información relevante.
- Programar, modificar y eliminar reuniones/eventos.
- Comunicarse con la aplicación mediante interfaz de terminal.

---

## **3 Requerimientos Funcionales**

### **3.1 Gestión de Contactos**
- [ ] **RF-01:** Registrar un nuevo contacto.
- [ ] **RF-02:** Editar la información de un contacto existente.
- [ ] **RF-03:** Eliminar un contacto.
- [ ] **RF-04:** Buscar y filtrar contactos por sus campos.

#### **3.1.1 Clase Contact**
- [ ] **RF-05:** El contacto está definido por:
    - Nombre (obligatorio) -> String
    - Número de teléfono (opcional) -> Int
    - Correo Electrónico (opcional) -> String
- [ ] **RF-06**: Debe haber un constructor que respete lo anterior.
- [x] **RF-07:** Todos los campos deben ser privados.
- [x] **RF-08:** Todos los campos deben ser accedidos por getters
- [ ] **RF-09:** Todos los campos deben ser modificados con setters.
- [x] **RF-10:** Todos los campos deben poder ser printeables.
- [x] **RF-11:** La clase debe poder ser printeable.

#### **3.1.2 Clase AddressBook**
- [ ] **RF-12:** Se debe usar *{inserte estructura de datos escogida}* para gestionar los contactos.
- [ ] **RF-13:** Se debe poder ingresar un nuevo contacto.
- [ ] **RF-14:** Se debe poder eliminar un contacto (esto implica eliminarlo de la memoria).
- [ ] **RF-15:** Se debe poder filtrar un contacto según el nombre y retornar todos los que tienen tal nombre.

### **3.2 Gestión de Calendario**
- [ ] **RF-16:** Crear eventos.
- [ ] **RF-17:** Modificar un evento existente.
- [ ] **RF-18:** Eliminar un evento.
- [ ] **RF-19:** Buscar y filtrar evento por sus campos.

#### **3.2.1 Clase Event**
- [ ] **RF-20:** El evento está definido por:
    - Título (obligatorio) -> String
    - Fecha (obligatorio) -> LocalDate
    - Hora de Inicio (obligatorio) -> LocalTime
    - Hora de Termino (obligatorio) -> LocalTime
    - Si dura todo el día (obligatorio) -> Boolean
    - Ubicación (opcional) -> String
    - Descripción (opcional) -> String
- [ ] **RF-21:** Debe haber un constructor que respete lo anterior.
- [x] **RF-22:** Todos los campos deben ser privados.
- [x] **RF-23:** Todos los campos deben ser accedidos por getters
- [ ] **RF-24:** Todos los campos deben ser modificados con setters.
- [x] **RF-25:** Todos los campos deben poder ser printeables.
- [x] **RF-26:** La clase debe poder ser printeable.

#### **3.2.2 Clase Calendar**
- [ ] **RF-27:** Se debe usar *{inserte estructura de datos escogida}* para gestionar los eventos.
- [ ] **RF-28:** Se debe poder ingresar un nuevo evento.
- [ ] **RF-29:** Se debe poder eliminar un evento (esto implica eliminarlo de la memoria).
- [ ] **RF-30:** Se debe poder filtrar un evento según el título (retornar todas las coincidencias).
- [ ] **RF-31:** Se debe poder filtrar un evento según la fecha (retornar todas las coincidencias).

### **3.3 Interfaz Gráfica**
- [ ] **RF-32:** Debe permitir mostrar AddressBookUI.
- [ ] **RF-33:** Debe permitir mostrar CalendarUI.
- [ ] **RF-34:** Debe permitir salir de la interfaz.

#### **3.3.1 Clase AddressBookUI**
- [ ] **RF-35:** Debe permitir listar todos los contactos.
- [ ] **RF-36:** Debe permitir mostrar un solo contacto.
- [ ] **RF-37:** Debe permitir añadir un contacto.
- [ ] **RF-38:** Debe permitir modificar los campos de un contacto existente.
- [ ] **RF-39:** Debe permitir eliminar un contacto (esto lo borraría de la memoria).
- [ ] **RF-40:** Debe permitir salir de la interfaz.

#### **3.3.2 Clase CalendarUI**
- [ ] **RF-41:** Debe permitir listar todos los eventos.
- [ ] **RF-42:** Debe permitir mostrar un solo evento.
- [ ] **RF-43:** Debe permitir añadir un evento.
- [ ] **RF-44:** Debe permitir modificar los campos de un evento existente.
- [ ] **RF-45:** Debe permitir eliminar un evento (esto lo borraría de la memoria).
- [ ] **RF-46:** Debe permitir salir de la interfaz.
