package my.projects.fishermenleaderboard.api.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

class FishermenHistoryTest {

    @Test
    void addFisherman_new() {
        FishermenHistory history = new FishermenHistory();
        history.addFisherman("0011", "Fabrizio", "Benvenuto");

        assertThat(history.collect(), hasSize(1));
        List<String> names = history.collect().stream().map(it -> it.name()).collect(Collectors.toList());
        assertThat(names, contains("Fabrizio Benvenuto"));
    }

    @Test
    void onRecollection_onExistingFisherman() {
        FishermenHistory history = new FishermenHistory();
        history.addFisherman("0011", "Fabrizio", "Benvenuto");
        history.onRecollection("0011", new BigDecimal("18.50"));

        List<String> amounts = history.collect().stream()
                .map(it -> it.amount().toPlainString())
                .collect(Collectors.toList());

        assertThat(amounts, contains("18.50"));
    }
}