package store.exception;

public enum ExceptionMessage {

    ERROR("[ERROR] "),
    INPUT_TYPE_ERROR("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    INPUT_PRODUCT_NOT_FOUND_ERROR("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    INPUT_PRODUCT_AMOUNT_ERROR("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    INPUT_ERROR("잘못된 입력입니다. 다시 입력해 주세요."),
    PRODUCT_FILE_READ_ERROR("상품 목록 파일을 읽을 수 없습니다."),
    PROMOTION_FILE_READ_ERROR("프로모션 목록 파일을 읽을 수 없습니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR.message + message;
    }
}
