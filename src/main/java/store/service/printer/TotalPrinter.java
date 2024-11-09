package store.service.printer;

import java.util.ArrayList;
import java.util.List;
import store.domain.Result;
import store.domain.Total;

public class TotalPrinter {

    private final List<Result> results;
    private final List<Total> totalResult = new ArrayList<>();
    private Integer totalPrice = 0;

    public TotalPrinter(List<Result> results) {
        this.results = results;
    }

    public List<Total> calculate() {
        for (Result result : results) {
            totalResult.add(new Total(result.getName(), result.calculateTotalAmount(), result.calculateTotalPrice()));
            totalPrice += result.calculateTotalPrice();
        }
        return totalResult;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }
}
