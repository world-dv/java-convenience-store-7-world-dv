package store.service.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Payment;
import store.domain.Product;

class OriginalCalculatorTest {

    @DisplayName("정가 결제 내역을 생성한다.")
    @Test
    void 정가_결제_내역을_생성한다() {
        Product product = new Product(1000, 10, "null");
        int buyAmount = 3;

        OriginalCalculator test = new OriginalCalculator(product, buyAmount);

        Payment trueResult = new Payment(3, 0, 1000, 0, 0);
        Payment result = test.calculate();

        assertThat(result.getBuyAmount()).isEqualTo(trueResult.getBuyAmount());
        assertThat(result.getFreeAmount()).isEqualTo(trueResult.getFreeAmount());
        assertThat(result.getExtraAmount()).isEqualTo(trueResult.getExtraAmount());
        assertThat(result.getPrice()).isEqualTo(trueResult.getPrice());
        assertThat(result.getMorePayAmount()).isEqualTo(trueResult.getMorePayAmount());
    }
}