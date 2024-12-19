package lab6.exceptions;

public class WrongLoginException extends RuntimeException {
    public WrongLoginException(String message) {
        super(message);
    }

    public WrongLoginException() {
        super("Wrong login");
    }
}
