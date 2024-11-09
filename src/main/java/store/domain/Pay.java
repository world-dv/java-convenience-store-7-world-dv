package store.domain;

public class Pay {

    private Integer buyAmount;
    private Integer freeAmount;
    private final Integer price;
    private Integer extra;
    private Boolean dateRange;

    public Pay(Integer buyAmount, Integer freeAmount, Integer price, Integer extra) {
        this.buyAmount = buyAmount;
        this.freeAmount = freeAmount;
        this.price = price;
        this.extra = extra;
        this.dateRange = false;
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

    public void changeDateRange() {
        this.dateRange = true;
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

    public Boolean getDateRange() {
        return dateRange;
    }

    public Integer getTotalAmount() {
        return buyAmount + freeAmount;
    }

    public Integer getTotalPrice() {
        return getTotalAmount() * price;
    }
}
