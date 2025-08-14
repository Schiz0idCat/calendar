package contacs;

public class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.setName(name);
        this.setPhone(phone);
        this.setEmail(email);
    }

    // Getters
    public String getName() {

    }

    public String getPhone() {

    }

    public String getEmail() {

    }

    // Setters
    public void setName(String name) {

    }

    public void setPhone(String phone) {

    }

    public void setEmail(String email) {

    }

    // Formatter
    @Override
    public String toString() {
        return "Name: " + this.getName() + "\n" +
               "Phone: " + this.getPhone() + "\n" +
               "Email: " + this.getEmail();
    }
}
