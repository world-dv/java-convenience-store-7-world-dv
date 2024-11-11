package store.service.printer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Payment;
import store.domain.Result;

class TotalPrinterTest {

    private final String name = "test";
    private Result result;

    @BeforeEach
    void before_each() {
        Payment originalResult = new Payment(5,  0, 1000, 0 ,0);
        Payment promotionResult = new Payment(3, 2, 1000, 2, 0);
        result = new Result(name, originalResult, promotionResult);
    }

    @DisplayName("구입한 상품의 총 합을 구합니다.")
    @Test
    void 구입한_상품의_총_합을_구합니다() {
        TotalPrinter test = new TotalPrinter(List.of(result));

        int trueResult = 5 * 1000 + 5 * 1000;
        int result = test.calculateTotalPrice();

        assertThat(result).isEqualTo(trueResult);
    }

    @DisplayName("구입한 상품의 총 수량을 구합니다.")
    @Test
    void 구입한_상품의_총_수량을_구합니다() {
        TotalPrinter test = new TotalPrinter(List.of(result));

        int trueResult = 10;
        int result = test.calculateTotalAmount();

        assertThat(result).isEqualTo(trueResult);
    }
}