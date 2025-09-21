package errors.modules.people;

public class InvalidNameException extends PersonException {
    public InvalidNameException(String message) {
        super(message);
    }
}
