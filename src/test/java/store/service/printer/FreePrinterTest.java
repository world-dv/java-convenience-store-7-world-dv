package store.service.printer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Payment;
import store.domain.Result;

class FreePrinterTest {

    private final String name = "test";
    private Result result;

    @BeforeEach
    void before_each() {
        Payment originalResult = new Payment(5,  0, 1000, 0 ,0);
        Payment promotionResult = new Payment(3, 2, 1000, 2, 0);
        result = new Result(name, originalResult, promotionResult);
    }

    @DisplayName("할인 받은 증정품 가격의 총 합을 구합니다.")
    @Test
    void 할인_받은_증정품_가격의_총_합을_구합니다() {
        FreePrinter freePrinter = new FreePrinter(List.of(result));

        int trueResult = 2000;
        int result = freePrinter.calculateTotalFree();

        assertThat(result).isEqualTo(trueResult);
    }
}