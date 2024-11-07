package store.view;

import java.util.HashMap;
import java.util.List;
import store.domain.Product;

public interface Output {

    static void printMessage(PrintMessage printMessage) {
        System.out.println(printMessage.getMessage());
    }

    void printlnMessage(PrintMessage printMessage);

    void printProduct(HashMap<String, List<Product>> products);
}
