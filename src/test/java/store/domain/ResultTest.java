package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    private final String name = "test";
    private Payment originalResult;
    private Payment promotionResult;

    @BeforeEach
    void before_each() {
        originalResult = new Payment(5,  0, 1000, 0 ,0);
        promotionResult = new Payment(3, 2, 1000, 2, 0);
    }

    @DisplayName("총 구입 수량을 구한다.")
    @Test
    void 총_구입_수량을_구한다() {
        Result test = new Result(name, originalResult, promotionResult);
        Integer trueResult = 10;

        Integer result = test.calculateTotalAmount();
        assertThat(result).isEqualTo(trueResult);
    }

    @DisplayName("총 구입 금액을 구한다.")
    @Test
    void 총_구입_금액을_구한다() {
        Result test = new Result(name, originalResult, promotionResult);
        Integer trueResult = 5 * 1000 + (3 + 2) * 1000;

        Integer result = test.calculateTotalPrice();
        assertThat(result).isEqualTo(trueResult);
    }

    @DisplayName("증정 수량을 구한다.")
    @Test
    void 증정_수량을_구한다() {
        Result test = new Result(name, originalResult, promotionResult);
        Integer trueResult = 2;

        Integer result = test.calculateFreeAmount();
        assertThat(result).isEqualTo(trueResult);
    }

    @DisplayName("증정 금액을 구한다.")
    @Test
    void 증정_금액을_구한다() {
        Result test = new Result(name, originalResult, promotionResult);
        Integer trueResult = 2 * 1000;

        Integer result = test.calculateFreePrice();
        assertThat(result).isEqualTo(trueResult);
    }

    @DisplayName("프로모션 날짜에 포함되는 경우 전체 금액을 구한다.")
    @Test
    void 프로모션_날짜에_포함되는_경우_전체_금액을_구한다() {
        Result test = new Result(name, originalResult, promotionResult);
        Integer trueResult = 5 * 1000 + 2 * 1000;

        promotionResult.changeMembership();

        Integer result = test.calculateMembership();
        assertThat(result).isEqualTo(trueResult);
    }

    @DisplayName("프로모션 날짜에 포함되지 않는 경우 전체 금액을 구한다.")
    @Test
    void 프로모션_날짜에_포함되지_않는_경우_전체_금액을_구한다() {
        Result test = new Result(name, originalResult, promotionResult);
        Integer trueResult = 5 * 1000 + (3 + 2) * 1000;

        promotionResult.changeMembership();
        promotionResult.changeDateRange();

        Integer result = test.calculateMembership();
        assertThat(result).isEqualTo(trueResult);
    }
}