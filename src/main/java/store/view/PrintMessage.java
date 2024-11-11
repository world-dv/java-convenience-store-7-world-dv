package store.view;

public enum PrintMessage {

    PRODUCT_INFO_HEADER_FORMAT("%-15s%-10s%-10s"),
    START_MESSAGE("안녕하세요. W편의점입니다."),
    WELCOME_MESSAGE("현재 보유하고 있는 상품입니다." + System.lineSeparator()),
    PRODUCT_DETAIL("- %s %s원 %s %s"),
    INPUT_ITEM_MESSAGE("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    INPUT_EXTRA_FREE_MESSAGE("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),
    INPUT_EXTRA_PAY_MESSAGE("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)" + System.lineSeparator()),
    INPUT_MEMBERSHIP_MESSAGE("멤버십 할인을 받으시겠습니까? (Y/N)"),
    LINE_CONVENIENCE_STORE("============== W 편의점 =============="),
    TABLE_SETTING(String.format(PRODUCT_INFO_HEADER_FORMAT.getMessage(), "상품명", "수량", "금액")),
    WISH_DETAIL(PRODUCT_INFO_HEADER_FORMAT.getMessage()),
    LINE_FREE("============== 증 정 =============="),
    FREE_DETAIL("%-15s%-10s"),
    LINE("===================================="),
    TOTAL_PRICE(PRODUCT_INFO_HEADER_FORMAT.getMessage()),
    PROMOTION_DISCOUNT_PRICE("%-25s-%-10s"),
    MEMBERSHIP_DISCOUNT_PRICE("%-25s-%-10s"),
    PAY_PRICE("%-25s%-10s"),
    INPUT_RESTART_MESSAGE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),
    PRODUCT_NOTHING("재고 없음"),
    AMOUNT_UNIT("개"),
    LINE_SPACE("");

    private final String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
