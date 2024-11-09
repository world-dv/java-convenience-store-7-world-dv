package store.service;

import java.util.ArrayList;
import java.util.List;
import store.domain.Free;
import store.domain.Result;

public class FreePrinter {

    private final List<Result> results;
    private final List<Free> freeResult = new ArrayList<>();
    private Integer totalFree = 0;

    public FreePrinter(List<Result> results) {
        this.results = results;
    }

    public List<Free> calculate() {
        for (Result result : results) {
            freeResult.add(new Free(result.getName(), result.calculateFreeAmount()));
            totalFree += result.calculateFreePrice();
        }
        return freeResult;
    }

    public Integer getTotalFree() {
        return totalFree;
    }
}
