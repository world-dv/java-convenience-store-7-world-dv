package store.domain;

public class Pay {

    private Integer buyAmount;
    private Integer freeAmount;
    private final Integer price;
    private Integer extra;

    public Pay(Integer buyAmount, Integer freeAmount, Integer price, Integer extra) {
        this.buyAmount = buyAmount;
        this.freeAmount = freeAmount;
        this.price = price;
        this.extra = extra;
    }

    public void addBuyAmount(Integer extraAmount) {
        this.buyAmount += extraAmount;
    }

    public void addFreeAmount(Integer extraAmount) {
        this.freeAmount += extraAmount;
    }

    public void setFreeAmount(Integer freeAmount) {
        this.freeAmount = freeAmount;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public Integer getFreeAmount() {
        return freeAmount;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getExtra() {
        return extra;
    }
}
