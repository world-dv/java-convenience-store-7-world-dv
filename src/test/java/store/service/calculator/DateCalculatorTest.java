package store.service.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Payment;
import store.domain.Promotion;

class DateCalculatorTest {

    private Payment payResult;

    @BeforeEach
    void before_each() {
        payResult = new Payment(3, 2, 1000, -1, 0);
    }

    @DisplayName("오늘 날짜가 프로모션 기간에 속하면 상태를 변경하지 않는다.")
    @Test
    void 오늘_날짜가_프로모션_기간에_속하면_상태를_변경하지_않는다() {
        Promotion promotion = new Promotion(List.of(2, 1), List.of("2024-11-01", "2024-12-01"));

        DateCalculator test = new DateCalculator(promotion, payResult);
        test.calculate();

        assertThat(payResult.getDateRange()).isFalse();
        assertThat(payResult.getBuyAmount()).isEqualTo(3);
        assertThat(payResult.getFreeAmount()).isEqualTo(2);
        assertThat(payResult.getExtraAmount()).isEqualTo(-1);
    }

    @DisplayName("오늘 날짜가 프로모션 기간에 속하지 않으면 상태를 변경한다.")
    @Test
    void 오늘_날짜가_프로모션_기간에_속하지_않으면_상태를_변경한다() {
        Promotion promotion = new Promotion(List.of(2, 1), List.of("2024-10-01", "2024-11-01"));

        DateCalculator test = new DateCalculator(promotion, payResult);
        test.calculate();

        assertThat(payResult.getDateRange()).isTrue();
        assertThat(payResult.getBuyAmount()).isEqualTo(3 + 2);
        assertThat(payResult.getFreeAmount()).isEqualTo(0);
        assertThat(payResult.getExtraAmount()).isEqualTo(0);
    }
}