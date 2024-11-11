package store.service.printer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Payment;
import store.domain.Result;

class MembershipPrinterTest {

    private final String name = "test";

    @DisplayName("추가 결제 금액일 경우 멤버십 할인 금액을 구합니다.")
    @Test
    void 추가_결제_금액일_경우_멤버십_할인_금액을_구합니다() {
        Payment originalResult = new Payment(5,  0, 1000, 0 ,0);
        Payment promotionResult = new Payment(3, 2, 1000, 2, 0);
        promotionResult.changeMembership();
        Result test = new Result(name, originalResult, promotionResult);
        MembershipPrinter membershipPrinter = new MembershipPrinter(List.of(test));

        int trueResult = (int) ((5 + 2) * 1000 * 0.3);
        int result = membershipPrinter.calculate("Y");

        assertThat(result).isEqualTo(trueResult);
    }

    @DisplayName("프로모션 미적용 금액일 경우 멤버십 할인 금액을 구합니다.")
    @Test
    void 프로모션_미적용_금액일_경우_멤버십_할인_금액을_구합니다() {
        Payment originalResult = new Payment(5,  0, 1000, 0 ,0);
        Payment promotionResult = new Payment(3, 2, 1000, 2, 0);
        promotionResult.changeDateRange();
        Result test = new Result(name, originalResult, promotionResult);
        MembershipPrinter membershipPrinter = new MembershipPrinter(List.of(test));

        int trueResult = (int) (10 * 1000 * 0.3);
        int result = membershipPrinter.calculate("Y");

        assertThat(result).isEqualTo(trueResult);
    }
}