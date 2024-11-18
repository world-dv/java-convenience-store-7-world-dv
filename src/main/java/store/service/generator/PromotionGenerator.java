package store.service.generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import store.domain.Promotion;
import store.exception.ExceptionMessage;
import store.util.Separator;

public class PromotionGenerator {

    private static final String PROMOTION_DIRECTORY = "user.dir";
    private static final String PROMOTION_FILE_PATH = "\\src\\main\\resources\\promotions.md";
    private static final Integer BUY = 1;
    private static final Integer GET = 2;
    private static final Integer START_DATE = 3;
    private static final Integer END_DATE = 4;

    private final HashMap<String, Promotion> promotions = new LinkedHashMap<>();

    public HashMap<String, Promotion> generate() {
        try {
            BufferedReader file = new BufferedReader(
                    new FileReader(System.getProperty(PROMOTION_DIRECTORY) + PROMOTION_FILE_PATH));
            readFile(file);
        } catch (IOException e) {
            throw new IllegalArgumentException(ExceptionMessage.PROMOTION_FILE_READ_ERROR.getMessage());
        }
        return promotions;
    }

    private void readFile(BufferedReader file) throws IOException {
        String data = file.readLine();
        while (true) {
            data = file.readLine();
            if (data == null) {
                break;
            }
            List<String> promotion = Separator.splitItems(data);
            updatePromotion(promotion.getFirst(), createNew(promotion));
        }
    }

    private void updatePromotion(String name, Promotion newPromotion) {
        promotions.put(name, newPromotion);
    }

    private Promotion createNew(List<String> promotion) {
        return new Promotion(createContent(promotion), createDate(promotion));
    }

    private List<Integer> createContent(List<String> promotion) {
        return List.of(changeNumber(promotion.get(BUY)), changeNumber(promotion.get(GET)));
    }

    private List<String> createDate(List<String> promotion) {
        return List.of(promotion.get(START_DATE), promotion.get(END_DATE));
    }

    private Integer changeNumber(String number) {
        return Integer.parseInt(number);
    }
}
