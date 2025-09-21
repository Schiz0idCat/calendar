package errors.modules.calendar;

public class InvalidDateException extends EventException {
    public InvalidDateException(String message) {
        super(message);
    }
}
