package store.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import store.domain.Product;

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
        return setDecimalFormat(quantity);
    }
}
