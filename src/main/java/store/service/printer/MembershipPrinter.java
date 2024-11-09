package store.service.printer;

import java.util.List;
import store.domain.Result;

public class MembershipPrinter {

    private final List<Result> results;

    public MembershipPrinter(List<Result> results) {
        this.results = results;
    }

    private Integer calculate() {
        int total = 0;
        for (Result result : results) {
            total += result.calculateMembership();
        }
        return (int) (total * 0.3);
    }

    public Integer getDiscount(String input) {
        if (input.equals("Y")) {
            return Math.min(calculate(), 8000);
        }
        return 0;
    }
}
