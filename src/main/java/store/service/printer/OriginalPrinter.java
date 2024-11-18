package store.service.printer;

import java.util.ArrayList;
import java.util.List;
import store.domain.Free;
import store.domain.Result;

public class OriginalPrinter {

    private static final Integer INIT_VALUE = 0;

    private final List<Result> results;

    public OriginalPrinter(List<Result> results) {
        this.results = results;
    }

    public Integer calculateOriginal() {
        int total = INIT_VALUE;
        for (Result result : results) {
            total += result.calculateOriginal();
        }
        return total;
    }

}
