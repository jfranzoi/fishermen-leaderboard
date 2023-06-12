package my.projects.fishermenleaderboard.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

class FishermanTest {

    private URL jpeg;

    @BeforeEach
    void setUp() throws Exception {
        jpeg = new URL("https://ogyre.fra1.digitaloceanspaces.com/any.jpeg");
    }

    @Test
    void recollections_lastOnesByDate() {
        Fisherman fisherman = new Fisherman("0011");

        fisherman.add(new Recollection("00aa", new BigDecimal("12.80"), ZonedDateTime.now().minusDays(15), jpeg))
                .add(new Recollection("00bb", new BigDecimal("10.40"), ZonedDateTime.now().minusDays(8), jpeg))
                .add(new Recollection("00cc", new BigDecimal("18.50"), ZonedDateTime.now().minusDays(5), jpeg));

        List<String> amounts = fisherman.lastRecollections(2).stream()
                .map(it -> it.amount().toPlainString())
                .collect(Collectors.toList());

        assertThat(amounts, contains(
                "18.50",
                "10.40"
        ));
    }
}