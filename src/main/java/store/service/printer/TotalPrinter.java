package store.service.printer;

import java.util.ArrayList;
import java.util.List;
import store.domain.Result;
import store.domain.Total;

public class TotalPrinter {

    private final List<Result> results;

    public TotalPrinter(List<Result> results) {
        this.results = results;
    }

    public List<Total> calculate() {
        List<Total> totalResult = new ArrayList<>();
        for (Result result : results) {
            totalResult.add(createTotal(result));
        }
        return totalResult;
    }

    private Total createTotal(Result result) {
        return new Total(result.getName(), result.calculateTotalAmount(), result.calculateTotalPrice());
    }

    public Integer calculateTotalPrice() {
        int total = 0;
        for (Result result : results) {
            total += result.calculateTotalPrice();
        }
        return total;
    }

    public Integer calculateTotalAmount() {
        int total = 0;
        for (Result result : results) {
            total += result.calculateTotalAmount();
        }
        return total;
    }
}
