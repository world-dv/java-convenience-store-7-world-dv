package store.domain;

public class Payment {

    private static final Integer GET_NOTHING = 0;
    private static final Integer GET_EXTRA_FREE = -1;

    private final Integer price;
    private final Integer morePayAmount;
    private Integer extraAmount;
    private Integer buyAmount;
    private Integer freeAmount;
    private Boolean dateRange;

    public Payment(Integer buyAmount, Integer freeAmount, Integer price, Integer extraAmount, Integer morePayAmount) {
        this.buyAmount = buyAmount;
        this.freeAmount = freeAmount;
        this.price = price;
        this.extraAmount = extraAmount;
        this.morePayAmount = morePayAmount;
        this.dateRange = false;
    }

    public boolean haveExtra() {
        return extraAmount > GET_NOTHING;
    }

    public boolean haveExtraFree() {
        return extraAmount.equals(GET_EXTRA_FREE);
    }

    public void addFreeAmount(Integer extraAmount) {
        this.freeAmount += extraAmount;
    }

    public Integer sumTotalAmount() {
        return buyAmount + freeAmount;
    }

    public Integer calculateTotalPrice() {
        return sumTotalAmount() * price;
    }

    public void changeDateRange() {
        this.dateRange = true;
    }

    public void addBuyAmount(Integer freeAmount) {
        this.buyAmount += freeAmount;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }

    public void setFreeAmount(Integer freeAmount) {
        this.freeAmount = freeAmount;
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

    public Integer getExtraAmount() {
        return extraAmount;
    }

    public void setExtraAmount(Integer extraAmount) {
        this.extraAmount = extraAmount;
    }

    public Integer getMorePayAmount() {
        return morePayAmount;
    }

    public Boolean getDateRange() {
        return dateRange;
    }
}
