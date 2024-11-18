package store.service.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Payment;
import store.domain.Product;
import store.domain.Promotion;

class PromotionCalculatorTest {

    private Product product;
    private Promotion promotion;

    @BeforeEach
    void before_each() {
        product = new Product(1000, 10, "반짝 할인");
        promotion =  new Promotion(List.of(2, 1), List.of("2024-11-01", "2024-12-01"));
    }

    @DisplayName("주어진 수량보다 많은 수량을 살 경우 프로모션 결제 내역을 생성한다.")
    @Test
    void 주어진_수량보다_많은_수량을_살_경우_프로모션_결제_내역을_생성한다() {
        int input = 100;
        PromotionCalculator test = new PromotionCalculator(product, promotion, input);
        Payment trueResult = new Payment(6, 3, 1000, 1, 90);

        Payment result = test.calculate(false);

        assertThat(result.getBuyAmount()).isEqualTo(trueResult.getBuyAmount());
        assertThat(result.getFreeAmount()).isEqualTo(trueResult.getFreeAmount());
        assertThat(result.getPrice()).isEqualTo(trueResult.getPrice());
        assertThat(result.getExtraAmount()).isEqualTo(trueResult.getExtraAmount());
        assertThat(result.getMorePayAmount()).isEqualTo(trueResult.getMorePayAmount());
    }

    @DisplayName("주어진 수량보다 적거나 같은 수량을 살 경우 프로모션 결제 내역을 생성한다.")
    @Test
    void 주어진_수량보다_적거나_같은_수량을_살_경우_프로모션_결제_내역을_생성한다() {
        int input = 3;
        PromotionCalculator test = new PromotionCalculator(product, promotion, input);
        Payment trueResult = new Payment(2, 1, 1000, 0, 0);

        Payment result = test.calculate(false);

        assertThat(result.getBuyAmount()).isEqualTo(trueResult.getBuyAmount());
        assertThat(result.getFreeAmount()).isEqualTo(trueResult.getFreeAmount());
        assertThat(result.getPrice()).isEqualTo(trueResult.getPrice());
        assertThat(result.getExtraAmount()).isEqualTo(trueResult.getExtraAmount());
        assertThat(result.getMorePayAmount()).isEqualTo(trueResult.getMorePayAmount());
    }

    @DisplayName("추가 증정을 입력받을 경우 프로모션 결제 내역을 생성한다.")
    @Test
    void 추가_증정을_입력받을_경우_프로모션_결제_내역을_생성한다() {
        int input = 2;
        PromotionCalculator test = new PromotionCalculator(product, promotion, input);
        Payment trueResult = new Payment(2, 0, 1000, -1, 0);

        Payment result = test.calculate(false);

        assertThat(result.getBuyAmount()).isEqualTo(trueResult.getBuyAmount());
        assertThat(result.getFreeAmount()).isEqualTo(trueResult.getFreeAmount());
        assertThat(result.getPrice()).isEqualTo(trueResult.getPrice());
        assertThat(result.getExtraAmount()).isEqualTo(trueResult.getExtraAmount());
        assertThat(result.getMorePayAmount()).isEqualTo(trueResult.getMorePayAmount());
    }
}