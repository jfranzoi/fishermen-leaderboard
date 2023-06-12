package my.projects.fishermenleaderboard.api.domain;

import java.math.BigDecimal;
import java.net.URL;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Fisherman add(Recollection recollection) {
        recollectionsById.put(recollection.id, recollection);
        return this;
    }

    public BigDecimal amountIn(Period period) {
        return recollectionsById.values().stream()
                .filter(it -> it.date.isAfter(ZonedDateTime.now().minus(period)))
                .map(it -> it.amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Collection<Recollection> lastRecollections(int occurrences) {
        return recollectionsById.values().stream()
                .sorted(mostRecentFirst())
                .limit(occurrences)
                .collect(Collectors.toList());
    }

    private Comparator<Recollection> mostRecentFirst() {
        return Comparator.comparing(Recollection::date).reversed();
    }
}
