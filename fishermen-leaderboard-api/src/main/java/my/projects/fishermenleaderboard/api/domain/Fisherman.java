package my.projects.fishermenleaderboard.api.domain;

import java.math.BigDecimal;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Fisherman {
    private final String id;
    private String name;
    private final List<Recollection> recollections;

    public Fisherman(String id) {
        this.id = id;
        this.recollections = new ArrayList<>();
    }

    public String id() {
        return id;
    }

    public Fisherman named(String name, String surname) {
        this.name = String.join(" ", name, surname);
        return this;
    }

    public String name() {
        return name;
    }

    public BigDecimal amountIn(Period period) {
        return recollections.stream()
                .filter(it -> it.date.isAfter(ZonedDateTime.now().minus(period)))
                .map(it -> it.amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addRecollection(BigDecimal amount, ZonedDateTime date) {
        recollections.add(new Recollection(amount, date));
    }
}
