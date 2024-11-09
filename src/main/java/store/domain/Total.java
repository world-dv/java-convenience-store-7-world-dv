package store.domain;

public class Total {

    private final String name;
    private final Integer totalAmount;
    private final Integer totalPrice;

    public Total(String name, Integer totalAmount, Integer totalPrice) {
        this.name = name;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }
}
