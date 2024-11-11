package store.service.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.Promotion;

class PromotionGeneratorTest {

    @DisplayName("주어진 경로 내의 프로모션 파일을 읽어 저장한다.")
    @Test
    void 주어진_경로_내의_프로모션_파일을_읽어_저장한다() {
        String testName = "MD추천상품";
        int testBuy = 1;
        int testGet = 1;
        String testStartDate = "2024-01-01";
        String testEndDate = "2024-12-31";

        HashMap<String, Promotion> test = new PromotionGenerator().generate();
        Promotion result = test.get(testName);

        assertThat(result.getBuy()).isEqualTo(testBuy);
        assertThat(result.getGet()).isEqualTo(testGet);
        assertThat(result.getStartDate()).isEqualTo(testStartDate);
        assertThat(result.getEndDate()).isEqualTo(testEndDate);
    }
}