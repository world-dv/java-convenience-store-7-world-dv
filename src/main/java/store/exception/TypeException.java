package store.exception;

public class TypeException extends IllegalArgumentException {

    public TypeException() {
        super(ExceptionMessage.INPUT_TYPE_ERROR.getMessage());
    }
}
