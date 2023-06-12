package my.projects.fishermenleaderboard.api.domain;

import java.math.BigDecimal;
import java.net.URL;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class Fisherman {
    private final String id;
    private final Map<String, Recollection> recollectionsById;
    private String name;
    private URL picture;

    public Fisherman(String id) {
        this.id = id;
        this.recollectionsById = new HashMap<>();
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

    public Fisherman picture(URL picture) {
        this.picture = picture;
        return this;
    }

    public URL picture() {
        return picture;
    }

    public void add(Recollection recollection) {
        recollectionsById.put(recollection.id, recollection);
    }

    public BigDecimal amountIn(Period period) {
        return recollectionsById.values().stream()
                .filter(it -> it.date.isAfter(ZonedDateTime.now().minus(period)))
                .map(it -> it.amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
