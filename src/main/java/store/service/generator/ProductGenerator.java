package store.service.generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import store.domain.Product;
import store.exception.ExceptionMessage;
import store.util.Separator;

public class ProductGenerator {

    private static final String PROPERTY_DIRECTORY = "user.dir";
    private static final String PRODUCT_FILE_PATH = "\\src\\main\\resources\\products.md";
    private static final Integer PRICE = 1;
    private static final Integer QUANTITY = 2;
    private static final Integer PROMOTION = 3;

    private final HashMap<String, List<Product>> products = new LinkedHashMap<>();

    public HashMap<String, List<Product>> generate() {
        try {
            BufferedReader file = new BufferedReader(
                    new FileReader(System.getProperty(PROPERTY_DIRECTORY) + PRODUCT_FILE_PATH));
            readFile(file);
        } catch (IOException e) {
            throw new IllegalArgumentException(ExceptionMessage.PRODUCT_FILE_READ_ERROR.getMessage());
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
            List<String> product = Separator.splitItems(data);
            update(product.getFirst(), createNew(product));
        }
    }

    private void update(String name, Product newProduct) {
        if (products.containsKey(name)) {
            updateProduct(name, newProduct);
            return;
        }
        updateInitProduct(name, newProduct);
    }

    private void updateProduct(String name, Product product) {
        products.get(name).add(product);
    }

    private void updateInitProduct(String name, Product product) {
        List<Product> list = new ArrayList<>();
        list.add(product);
        products.put(name, list);
    }

    private Product createNew(List<String> product) {
        return new Product(changeNumber(product.get(PRICE)), changeNumber(product.get(QUANTITY)),
                product.get(PROMOTION));
    }

    private Integer changeNumber(String number) {
        return Integer.parseInt(number);
    }
}
