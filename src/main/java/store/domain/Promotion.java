package store.domain;

import java.util.List;

public class Promotion {

    private final List<Integer> content;
    private final List<String> date;

    public Promotion(List<Integer> content, List<String> date) {
        this.content = content;
        this.date = date;
    }

    public List<Integer> getContent() {
        return content;
    }

    public List<String> getDate() {
        return date;
    }
}
