package store.domain;

import java.util.List;

public record Promotion(List<Integer> content, List<String> date) {

    public Integer getBuy() {
        return content.getFirst();
    }

    public Integer getGet() {
        return content.getLast();
    }

    public String getStartDate() {
        return date.getFirst();
    }

    public String getEndDate() {
        return date.getLast();
    }
}
