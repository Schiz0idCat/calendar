package errors.modules.people;

public class InvalidEmailException extends PersonException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
