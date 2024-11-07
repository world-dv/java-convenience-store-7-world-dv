package store.domain;

public class Product {

    private final Integer price;
    private final Integer quantity;
    private final String promotion;

    public Product(Integer price, Integer quantity, String promotion) {
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getPromotion() {
        return promotion;
    }
}
