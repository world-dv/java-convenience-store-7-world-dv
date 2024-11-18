package store.service.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Promotion;

class DateCalculatorTest {

    @DisplayName("오늘이 프로모션 기간 내 속하지 않는지 검증한다.")
    @Test
    void 오늘이_프로모션_기간_내_속하지_않는지_검증한다() {
        Promotion test = new Promotion(List.of(1,1), List.of("2023-01-01", "2023-12-01"));
        Promotion trueTest = new Promotion(List.of(1,1), List.of("2024-01-01", "2024-12-01"));

        DateCalculator result = new DateCalculator(test);
        DateCalculator trueResult = new DateCalculator(trueTest);

        assertThat(result.calculate()).isTrue();
        assertThat(trueResult.calculate()).isFalse();
    }
}