package my.projects.fishermenleaderboard.api.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Fisherman {
    private final String id;
    private final List<Recollection> recollections;

    public Fisherman(String id) {
        this.id = id;
        this.recollections = new ArrayList<>();
    }

    public String id() {
        return id;
    }

    public BigDecimal amount() {
        return recollections.stream()
                .map(it -> it.amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addRecollection(BigDecimal amount) {
        recollections.add(new Recollection(amount));
    }
}
