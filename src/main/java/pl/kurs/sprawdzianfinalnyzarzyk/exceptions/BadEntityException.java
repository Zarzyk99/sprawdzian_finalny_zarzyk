package pl.kurs.sprawdzianfinalnyzarzyk.exceptions;

public class BadEntityException extends RuntimeException {

    public BadEntityException(String message) {
        super(message);
    }

    public BadEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
