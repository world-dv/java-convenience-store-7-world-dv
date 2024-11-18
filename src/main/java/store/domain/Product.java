package store.domain;

public class Product {

    private final Integer price;
    private final String promotion;
    private Integer quantity;

    public Product(Integer price, Integer quantity, String promotion) {
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public String getPromotion() {
        return promotion;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
