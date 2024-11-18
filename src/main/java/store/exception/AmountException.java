package store.exception;

public class AmountException extends IllegalArgumentException {

    public AmountException() {
        super(ExceptionMessage.INPUT_PRODUCT_AMOUNT_ERROR.getMessage());
    }
}
