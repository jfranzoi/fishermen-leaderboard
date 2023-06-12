package my.projects.fishermenleaderboard.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.URL;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

class FishermenHistoryTest {
    private Period oneMonth = Period.ofDays(30);
    private URL jpeg;

    @BeforeEach
    void setUp() throws Exception {
        jpeg = new URL("https://ogyre.fra1.digitaloceanspaces.com/any.jpeg");
    }

    @Test
    void addFisherman_new() {
        FishermenHistory history = new FishermenHistory();
        history.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);

        assertThat(history.collect(oneMonth), hasSize(1));

        List<String> names = history.collect(oneMonth).stream().map(it -> it.name()).collect(Collectors.toList());
        assertThat(names, contains("Fabrizio Benvenuto"));
    }

    @Test
    void addFisherman_update() {
        FishermenHistory history = new FishermenHistory();
        history.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);
        history.addFisherman("0011", "Fabrizio", "Benvenuto Jr", jpeg);

        assertThat(history.collect(oneMonth), hasSize(1));

        List<String> names = history.collect(oneMonth).stream().map(it -> it.name()).collect(Collectors.toList());
        assertThat(names, contains("Fabrizio Benvenuto Jr"));
    }

    @Test
    void addRecollection_onExistingFisherman() {
        FishermenHistory history = new FishermenHistory();
        history.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);
        history.addRecollection("0011", new Recollection("00aa", new BigDecimal("18.50"), ZonedDateTime.now(), jpeg));

        List<String> amounts = history.collect(oneMonth).stream()
                .map(it -> it.amountIn(oneMonth).toPlainString())
                .collect(Collectors.toList());

        assertThat(amounts, contains("18.50"));
    }

    @Test
    void addRecollection_onUnknownFisherman() {
        FishermenHistory history = new FishermenHistory();
        history.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);
        history.addRecollection("0099", new Recollection("00aa", new BigDecimal("18.50"), ZonedDateTime.now(), jpeg));

        List<String> amounts = history.collect(oneMonth).stream()
                .map(it -> it.amountIn(oneMonth).toPlainString())
                .collect(Collectors.toList());

        assertThat(amounts, contains("0"));
    }

    @Test
    void addRecollection_updateAlreadyExisting() {
        FishermenHistory history = new FishermenHistory();
        history.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);
        history.addRecollection("0011", new Recollection("00aa", new BigDecimal("10.40"), ZonedDateTime.now(), jpeg));
        history.addRecollection("0011", new Recollection("00aa", new BigDecimal("18.50"), ZonedDateTime.now(), jpeg));

        List<String> amounts = history.collect(oneMonth).stream()
                .map(it -> it.amountIn(oneMonth).toPlainString())
                .collect(Collectors.toList());

        assertThat(amounts, contains("18.50"));
    }


    @Test
    void collect_rankingByAmount() {
        FishermenHistory history = new FishermenHistory();

        history.addFisherman("0011", "Benedetto", "Carpi", jpeg);
        history.addRecollection("0011", new Recollection("00aa", new BigDecimal("0"), ZonedDateTime.now(), jpeg));

        history.addFisherman("0022", "Fabrizio", "Benvenuto", jpeg);
        history.addRecollection("0022", new Recollection("00aa", new BigDecimal("18.50"), ZonedDateTime.now(), jpeg));

        history.addFisherman("0033", "Paulo", "Alvarez", jpeg);
        history.addRecollection("0033", new Recollection("00aa", new BigDecimal("38.20"), ZonedDateTime.now(), jpeg));

        List<String> names = history.collect(oneMonth).stream()
                .map(it -> it.name())
                .collect(Collectors.toList());

        assertThat(names, contains(
                "Paulo Alvarez",
                "Fabrizio Benvenuto",
                "Benedetto Carpi"
        ));
    }

    @Test
    void collect_rankingWithinPeriod() {
        FishermenHistory history = new FishermenHistory();

        history.addFisherman("0011", "Benedetto", "Carpi", jpeg);
        history.addRecollection("0011", new Recollection("00aa", new BigDecimal("10.50"), ZonedDateTime.now().minusDays(10), jpeg));

        history.addFisherman("0022", "Fabrizio", "Benvenuto", jpeg);
        history.addRecollection("0022", new Recollection("00aa", new BigDecimal("18.50"), ZonedDateTime.now().minusDays(4), jpeg));

        history.addFisherman("0033", "Paulo", "Alvarez", jpeg);
        history.addRecollection("0033", new Recollection("00aa", new BigDecimal("38.20"), ZonedDateTime.now().minusDays(45), jpeg));

        List<String> names = history.collect(oneMonth).stream()
                .map(it -> it.name())
                .collect(Collectors.toList());

        assertThat(names, contains(
                "Fabrizio Benvenuto",
                "Benedetto Carpi",
                "Paulo Alvarez"
        ));
    }
}