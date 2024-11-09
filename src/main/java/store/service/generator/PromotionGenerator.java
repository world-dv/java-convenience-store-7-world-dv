package store.service.generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import store.domain.Promotion;
import store.util.Separator;

public class PromotionGenerator {

    private final HashMap<String, Promotion> products = new LinkedHashMap<>();

    public HashMap<String, Promotion> generate() {
        try {
            BufferedReader file = new BufferedReader(
                    new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\promotions.md"));
            readFile(file);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return products;
    }

    private void readFile(BufferedReader file) throws IOException {
        String data = file.readLine();
        while (true) {
            data = file.readLine();
            if (data == null) {
                break;
            }
            List<String> promotion = Separator.splitItems(data);
            update(promotion.getFirst(), createNew(promotion));
        }
    }

    private Promotion createNew(List<String> promotion) {
        List<Integer> content = List.of(Integer.parseInt(promotion.get(1)), Integer.parseInt(promotion.get(2)));
        List<String> date = List.of(promotion.get(3), promotion.get(4));
        return new Promotion(content, date);
    }

    private void update(String name, Promotion newPromotion) {
        ;
        products.put(name, newPromotion);
    }
}
