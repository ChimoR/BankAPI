package service.exception;

public class InvalidDataException extends Exception {

    public InvalidDataException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
