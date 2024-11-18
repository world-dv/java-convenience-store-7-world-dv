package store.exception;

public class NotFoundException extends IllegalArgumentException {

    public NotFoundException() {
        super(ExceptionMessage.INPUT_PRODUCT_NOT_FOUND_ERROR.getMessage());
    }
}
