package store.domain;

import store.exception.InputException;

public class Wish {

    private final String name;
    private final Integer amount;

    public Wish(String name, Integer amount) {
        validatePositive(amount);
        this.name = name;
        this.amount = amount;
    }

    private void validatePositive(Integer amount) {
        if (amount <= 0) {
            throw new InputException();
        }
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }
}
