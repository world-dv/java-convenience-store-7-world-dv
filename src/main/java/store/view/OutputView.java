package store.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import store.domain.Free;
import store.domain.Product;
import store.domain.Total;

public class OutputView implements Output {

    private final DecimalFormat decimalFormat = new DecimalFormat("###,###");

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
    }

    private String setProductDetail(String name, Product product) {
        return String.format(PrintMessage.PRODUCT_DETAIL.getMessage(), name, setDecimalFormat(product.getPrice()),
                setQuantityFormat(product.getQuantity()), setPromotionFormat(product.getPromotion()));
    }

    private String setDecimalFormat(Integer number) {
        return decimalFormat.format(number);
    }

    private String setPromotionFormat(String promotion) {
        if (promotion.equals("null")) {
            return "";
        }
        return promotion;
    }

    private String setQuantityFormat(Integer quantity) {
        if (quantity.equals(0)) {
            return "재고 없음";
        }
        return setDecimalFormat(quantity) + "개";
    }

    @Override
    public void printTotal(List<Total> totals) {
        List<String> totalDetail = new ArrayList<>();
        for (Total total : totals) {
            totalDetail.add(setTotalDetail(total));
        }
        totalDetail.forEach(System.out::println);
    }

    private String setTotalDetail(Total total) {
        return String.format(PrintMessage.WISH_DETAIL.getMessage(), total.getName(),
                setDecimalFormat(total.getTotalAmount()), setQuantityFormat(total.getTotalPrice()));
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

    private String setFreeDetail(Free free) {
        if (free.getTotalAmount().equals(0)) {
            return null;
        }
        return String.format(PrintMessage.FREE_DETAIL.getMessage(), free.getName(),
                setDecimalFormat(free.getTotalAmount()));
    }

    @Override
    public void printDetail(PrintMessage printMessage, Integer number) {
        System.out.println(setPrice(printMessage, number));
    }

    private String setPrice(PrintMessage message, Integer price) {
        return String.format(message.getMessage(), setDecimalFormat(price));
    }
}
