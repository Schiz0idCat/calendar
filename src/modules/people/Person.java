package modules.people;

import errors.modules.people.*;

public class Person {
    private String rut;
    private String name;
    private String email;
    private String phone;

    // Constructores
    public Person() {
        this.setRut();
        this.setName();
        this.setEmail();
        this.setPhone();
    }

    // Getters
    public String getRut() {
        return this.rut;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    // Setters
    public void setRut(String rut) throws InvalidRutException {
        if (rut == null) throw new InvalidRutException("rut cannot be null");
        if (rut.isEmpty()) throw new InvalidRutException("rut cannot be empty");

        this.rut = rut;
    }

    private void setRut() {
        this.rut = "12.345.678-9";
    }

    public void setName(String name) throws InvalidNameException {
        if (name == null) throw new InvalidNameException("name cannot be null");
        if (name.isEmpty()) throw new InvalidNameException("name cannot be empty");

        this.name = name;
    }

    private void setName() {
        this.name = "name";
    }

    public void setEmail(String email) throws InvalidEmailException {
        if (email == null) throw new InvalidEmailException("email cannot be null");

        this.email = email;
    }

    public void setEmail() {
        this.email = "";
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhone() {
        this.phone = "";
    }

    @Override
    public String toString() {
        return "Rut: " + this.getRut() + "\n" +
        "Name: " + this.getName() + "\n" +
        "Email: " + this.getEmail() + "\n" +
        "Phone: " + this.getPhone();
    }
}
