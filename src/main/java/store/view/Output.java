package store.view;

import java.util.HashMap;
import java.util.List;
import store.domain.Free;
import store.domain.Product;
import store.domain.Total;

public interface Output {

    static void printMessage(PrintMessage printMessage) {
        System.out.println(printMessage.getMessage());
    }

    void printlnMessage(PrintMessage printMessage);

    void printProduct(HashMap<String, List<Product>> products);

    static void printExtraMessage(PrintMessage printMessage, String name, Integer amount) {
        System.out.printf(printMessage.getMessage(), name, amount);
    }

    void printTotal(List<Total> totals);

    void printDetail(PrintMessage printMessage, Integer number);

    void printFree(List<Free> frees);
}
