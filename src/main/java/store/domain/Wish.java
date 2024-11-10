package store.domain;

import store.exception.InputException;

public record Wish(String name, Integer amount) {

    private static final Integer JUDGE_POSITIVE_NUMBER = 0;

    public Wish {
        validatePositive(amount);
    }

    private void validatePositive(Integer amount) {
        if (amount <= JUDGE_POSITIVE_NUMBER) {
            throw new InputException();
        }
    }
}
