package store.service.printer;

import java.util.ArrayList;
import java.util.List;
import store.domain.Free;
import store.domain.Result;

public class FreePrinter {

    private static final Integer INIT_VALUE = 0;

    private final List<Result> results;

    public FreePrinter(List<Result> results) {
        this.results = results;
    }

    public List<Free> calculate() {
        List<Free> freeResult = new ArrayList<>();
        for (Result result : results) {
            freeResult.add(createFree(result));
        }
        return freeResult;
    }

    private Free createFree(Result result) {
        return new Free(result.getName(), result.calculateFreeAmount());
    }

    public Integer calculateTotalFree() {
        int total = INIT_VALUE;
        for (Result result : results) {
            total += result.calculateFreePrice();
        }
        return total;
    }
}
