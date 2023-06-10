package my.projects.fishermenleaderboard.api.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FishermenHistory {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishermenHistory.class);

    private List<Fisherman> fishermen = new ArrayList<>();

    public void addFisherman(String id, String name, String surname) {
        LOGGER.info("Adding fisherman, id: {}, name: {}, surname: {}", id, name, surname);
        fishermen.add(new Fisherman(id).named(name, surname));
    }

    public void onRecollection(String fisherman, BigDecimal amount) {
        LOGGER.info("new recollection, fisherman: {}, amount: {}", fisherman, amount);
        fishermen.stream().filter(it -> fisherman.equals(it.id()))
                .forEach(it -> it.addRecollection(amount));
    }

    public List<Fisherman> collect() {
        return fishermen.stream()
                .sorted(Comparator.comparing(Fisherman::amount).reversed())
                .collect(Collectors.toList());
    }
}
