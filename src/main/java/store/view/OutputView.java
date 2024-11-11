package store.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import store.domain.Free;
import store.domain.Product;
import store.domain.Total;
import store.service.printer.TotalPrinter;

public class OutputView implements Output {

    private static final String NUMBER_UNIT_PATTEN = "###,###";
    private static final String PROMOTION_NOT_FOUND = "null";
    private static final String INIT_VALUE = "";
    private static final Integer AMOUNT_NOT_FOUND = 0;

    private final DecimalFormat decimalFormat = new DecimalFormat(NUMBER_UNIT_PATTEN);

    @Override
    public void printlnMessage(PrintMessage printMessage) {
        System.out.println(printMessage.getMessage());
    }

    @Override
    public void printProduct(HashMap<String, List<Product>> products) {
        List<String> productDetail = new ArrayList<>();
        for (String name : products.keySet()) {
            List<Product> product = products.get(name);
            product.forEach(value -> productDetail.add(setProductDetail(name, value)));
        }
        productDetail.forEach(System.out::println);
        printlnMessage(PrintMessage.LINE_SPACE);
    }

    private String setProductDetail(String name, Product product) {
        return String.format(PrintMessage.PRODUCT_DETAIL.getMessage(), name, setDecimalFormat(product.getPrice()),
                setQuantityFormat(product.getQuantity()), setPromotionFormat(product.getPromotion()));
    }

    private String setPromotionFormat(String promotion) {
        if (promotion.equals(PROMOTION_NOT_FOUND)) {
            return INIT_VALUE;
        }
        return promotion;
    }

    private String setDecimalFormat(int number) {
        return decimalFormat.format(number);
    }

    private String setQuantityFormat(int quantity) {
        if (quantity == AMOUNT_NOT_FOUND) {
            return PrintMessage.PRODUCT_NOTHING.getMessage();
        }
        return setDecimalFormat(quantity) + PrintMessage.AMOUNT_UNIT.getMessage();
    }

    private String setTotal(Total total) {
        return String.format(PrintMessage.WISH_DETAIL.getMessage(), total.name(),
                total.totalAmount(), setDecimalFormat(total.totalPrice()));
    }

    @Override
    public void printTotal(List<Total> totals) {
        List<String> totalDetail = new ArrayList<>();
        for (Total total : totals) {
            totalDetail.add(setTotal(total));
        }
        totalDetail.forEach(System.out::println);
    }

    private String setFreeDetail(Free free) {
        if (free.totalAmount().equals(AMOUNT_NOT_FOUND)) {
            return null;
        }
        return String.format(PrintMessage.FREE_DETAIL.getMessage(), free.name(), free.totalAmount());
    }

    @Override
    public void printFree(List<Free> frees) {
        List<String> freeDetail = new ArrayList<>();
        for (Free free : frees) {
            freeDetail.add(setFreeDetail(free));
        }
        freeDetail.stream().filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    private String setTotalDetail(TotalPrinter totalPrinter) {
        return String.format(PrintMessage.TOTAL_PRICE.getMessage(), "총구매액",
                totalPrinter.calculateTotalAmount(),
                setDecimalFormat(totalPrinter.calculateTotalPrice()));
    }

    @Override
    public void printTotalDetail(TotalPrinter totalPrinter) {
        System.out.println(setTotalDetail(totalPrinter));
    }

    private String setPrice(PrintMessage message, int price) {
        return String.format(message.getMessage(), setDecimalFormat(price));
    }

    @Override
    public void printDetail(PrintMessage printMessage, int number) {
        System.out.println(setPrice(printMessage, number));
    }

    public void printMembership(Integer discount) {
        System.out.printf((PrintMessage.MEMBERSHIP_DISCOUNT_PRICE.getMessage()) + "%n", "멤버십할인",
                setDecimalFormat(discount));
    }

    public void printPayMoney(Integer discount) {
        System.out.printf((PrintMessage.PAY_PRICE.getMessage()) + "%n", "내실돈", setDecimalFormat(discount));
    }

    public void printPromotion(Integer discount) {
        System.out.printf((PrintMessage.PROMOTION_DISCOUNT_PRICE.getMessage()) + "%n", "행사할인", discount);
    }
}
