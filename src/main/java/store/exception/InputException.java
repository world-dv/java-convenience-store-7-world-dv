package store.exception;

public class InputException extends IllegalArgumentException {

    public InputException() {
        super(ExceptionMessage.INPUT_ERROR.getMessage());
    }
}
