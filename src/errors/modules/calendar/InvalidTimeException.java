package errors.modules.calendar;

public class InvalidTimeException extends EventException {
    public InvalidTimeException(String message) {
        super(message);
    }
}
