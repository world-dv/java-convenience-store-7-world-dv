package store.service.generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import store.domain.Product;
import store.util.Separator;

public class ProductGenerator {

    private final HashMap<String, List<Product>> products = new LinkedHashMap<>();

    public HashMap<String, List<Product>> generate() {
        try {
            BufferedReader file = new BufferedReader(
                    new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\products.md"));
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
            List<String> product = Separator.splitItems(data);
            update(product.getFirst(), createNew(product));
        }
    }

    private Product createNew(List<String> product) {
        return new Product(Integer.parseInt(product.get(1)), Integer.parseInt(product.get(2)), product.getLast());
    }

    private void update(String name, Product newProduct) {
        if (products.containsKey(name)) {
            products.get(name).add(newProduct);
            return;
        }
        List<Product> list = new ArrayList<>();
        list.add(newProduct);
        products.put(name, list);
    }
}
