package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentTest {

    private Payment payment;

    @BeforeEach
    void before_each() {
        payment = new Payment(3, 2, 1000, 1, 3);
    }

    @DisplayName("추가 결제 금액이 있는지 확인합니다.")
    @Test
    void 추가_결제_금액이_있는지_확인합니다() {
        assertThat(payment.haveExtra()).isTrue();
    }

    @DisplayName("추가 증정 상품이 있는지 확인합니다.")
    @Test
    void 추가_증정_상품이_있는지_확인합니다() {
        assertThat(payment.haveExtraFree()).isFalse();

        payment.setExtraAmount(-1);
        assertThat(payment.haveExtraFree()).isTrue();
    }

    @DisplayName("추가 증정 상품을 추가합니다.")
    @Test
    void 추가_증정_상품을_추가합니다() {
        int extraAmount = 3;

        payment.addFreeAmount(extraAmount);

        assertThat(payment.getFreeAmount()).isEqualTo(5);
    }

    @DisplayName("총 상품 수량을 계산합니다.")
    @Test
    void 총_상품_수량을_계산합니다() {
        assertThat(payment.sumTotalAmount()).isEqualTo(5);
    }

    @DisplayName("총 상품 금액을 계산합니다.")
    @Test
    void 총_상품_금액을_계산합니다() {
        assertThat(payment.calculateTotalPrice()).isEqualTo(5000);
    }

    @DisplayName("프로모션 기간에 속할 경우 상태를 변경합니다.")
    @Test
    void 프로모션_기간에_속할_경우_상태를_변경합니다() {
        assertThat(payment.getDateRange()).isFalse();

        payment.changeDateRange();
        assertThat(payment.getDateRange()).isTrue();
    }

    @DisplayName("멤버십 계산에 속하는 금액일 경우 상태를 변경합니다.")
    @Test
    void 멤버십_계산에_속하는_금액일_경우_상태를_변경합니다() {
        assertThat(payment.getMembership()).isFalse();

        payment.changeMembership();
        assertThat(payment.getMembership()).isTrue();
    }

    @DisplayName("결제 상품을 추가합니다.")
    @Test
    void 결제_상품을_추가합니다() {
        payment.addBuyAmount(2);
        assertThat(payment.getBuyAmount()).isEqualTo(5);
    }

    @DisplayName("추가 결제 금액을 계산합니다.")
    @Test
    void 추가_결제_금액을_계산합니다() {
        payment.addFreeAmount(2);
        assertThat(payment.getFreeAmount()).isEqualTo(4);
    }
}