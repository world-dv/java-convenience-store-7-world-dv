package store.domain;

public class Free {

    private final String name;
    private final Integer totalAmount;

    public Free(String name, Integer totalAmount) {
        this.name = name;
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }
}
