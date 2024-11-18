package store.view;

import java.util.HashMap;
import java.util.List;
import store.domain.Free;
import store.domain.Product;
import store.domain.Total;
import store.service.printer.TotalPrinter;

public interface Output {

    void printlnMessage(PrintMessage printMessage);

    void printProduct(HashMap<String, List<Product>> products);

    void printTotalDetail(TotalPrinter totalPrinter);

    void printTotal(List<Total> totals);

    void printDetail(PrintMessage printMessage, int number);

    void printFree(List<Free> frees);
}
