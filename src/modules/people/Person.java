package modules.people;

public class Person {
    private String rut;
    private String name;
    private String email;
    private int phone;

    // Constructores
    public Person() {
        this.setRut();
        this.setName();
        this.setEmail();
        this.setPhone();
    }

    // Getters
    public String getRut() {
        return rut;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    // Setters
    public void setRut(String rut) {
        if (rut == null) throw new IllegalArgumentException("rut cannot be null");
        if (rut.isEmpty()) throw new IllegalArgumentException("rut cannot be empty");

        this.rut = rut;
    }

    private void setRut() {
        this.rut = "12.345.678-9";
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("name cannot be null");
        if (name.isEmpty()) throw new IllegalArgumentException("name cannot be empty");

        this.name = name;
    }

    private void setName() {
        this.name = "name";
    }

    public void setEmail(String email) {
        if (email == null) throw new IllegalArgumentException("email cannot be null");

        this.email = email;
    }

    public void setEmail() {
        this.email = "";
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setPhone() {
        this.phone = 0;
    }

    @Override
    public String toString() {
        return "Rut: " + this.getRut() + "\n" +
        "Name: " + this.getName() + "\n" +
        "Email: " + this.getEmail() + "\n" +
        "Phone: " + this.getPhone();
    }
}
