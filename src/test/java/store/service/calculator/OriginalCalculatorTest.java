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
        Product product = new Product(1000, 10, "반짝 할인");
        int buyAmount = 3;

        OriginalCalculator test = new OriginalCalculator(product, buyAmount);

        Payment trueResult = new Payment(3, 0, 1000, 0, 0);

        assertThat(test.calculate().getBuyAmount()).isEqualTo(trueResult.getBuyAmount());
        assertThat(test.calculate().getFreeAmount()).isEqualTo(trueResult.getFreeAmount());
        assertThat(test.calculate().getExtraAmount()).isEqualTo(trueResult.getExtraAmount());
        assertThat(test.calculate().getPrice()).isEqualTo(trueResult.getPrice());
        assertThat(test.calculate().getExtraAmount()).isEqualTo(trueResult.getExtraAmount());
        assertThat(test.calculate().getMorePayAmount()).isEqualTo(trueResult.getMorePayAmount());
    }
}