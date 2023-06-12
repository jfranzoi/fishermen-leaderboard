package my.projects.fishermenleaderboard.api.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FishermenHistory {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishermenHistory.class);

    private Map<String, Fisherman> fishermen;

    public FishermenHistory() {
        this.fishermen = new HashMap<>();
    }

    public Optional<Fisherman> by(String id) {
        return Optional.ofNullable(fishermen.get(id));
    }

    public void addFisherman(String id, String name, String surname, URL picture) {
        LOGGER.info("Adding fisherman, id: {}, name: {}, surname: {}", id, name, surname);
        by(id).orElseGet(() -> addWith(id))
                .named(name, surname)
                .picture(picture);
    }

    public void addRecollection(String fisherman, Recollection recollection) {
        LOGGER.info("Adding recollection, fisherman: {}, recollection: {}", fisherman, recollection);
        by(fisherman).ifPresent(it -> it.add(recollection));
    }

    public List<Fisherman> collect(Period period) {
        return fishermen.values().stream()
                .sorted(byRanking(period))
                .collect(Collectors.toList());
    }

    private Comparator<Fisherman> byRanking(Period period) {
        return Comparator.comparing((Fisherman it) -> it.amountIn(period)).reversed();
    }

    private Fisherman addWith(String id) {
        Fisherman created = new Fisherman(id);
        fishermen.put(id, created);
        return created;
    }
}
