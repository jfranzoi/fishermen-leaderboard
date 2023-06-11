package my.projects.fishermenleaderboard.api.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FishermenHistory {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishermenHistory.class);

    private List<Fisherman> fishermen = new ArrayList<>();

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
        return fishermen.stream()
                .sorted(Comparator.comparing((Fisherman it) -> it.amountIn(period)).reversed())
                .collect(Collectors.toList());
    }

    private Fisherman addWith(String id) {
        Fisherman created = new Fisherman(id);
        fishermen.add(created);
        return created;
    }

    private Optional<Fisherman> by(String id) {
        return fishermen.stream().filter(it -> id.equals(it.id())).findFirst();
    }
}
