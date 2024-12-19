package lab6.exceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) {
        super(message);
    }

    public WrongPasswordException() {
        super("Wrong password");
    }
}
