package my.projects.fishermenleaderboard.api.web;

import my.projects.fishermenleaderboard.api.domain.FishermenHistory;
import my.projects.fishermenleaderboard.api.domain.Recollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LeaderboardControllerTest {
    private URL jpeg;
    private MockMvc mvc;
    private FishermenHistory fishermenHistory;

    @BeforeEach
    void setUp() throws Exception {
        jpeg = new URL("https://ogyre.fra1.digitaloceanspaces.com/any.jpeg");

        fishermenHistory = new FishermenHistory();
        mvc = MockMvcBuilders.standaloneSetup(new LeaderboardController(fishermenHistory)).build();
    }

    @Test
    void index_contentMapping() throws Exception {
        mvc.perform(get("/fishermen"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void index_viewData() throws Exception {
        fishermenHistory.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);
        fishermenHistory.addRecollection("0011", new Recollection(
                "00aa", new BigDecimal("30.5"), ZonedDateTime.now(), jpeg
        ));

        mvc.perform(get("/fishermen?size=5&page=1"))
                .andExpect(jsonPath("$[0].id").value("0011"))
                .andExpect(jsonPath("$[0].name").value("Fabrizio Benvenuto"))
                .andExpect(jsonPath("$[0].amount").value("30.5"))
                .andExpect(jsonPath("$[0].picture").value(jpeg.toString()));
    }

    @Test
    void index_noData() throws Exception {
        mvc.perform(get("/fishermen"))
                .andExpect(jsonPath("$.size()").value("0"));
    }

    @Test
    void index_withinPeriod() throws Exception {
        fishermenHistory.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);
        fishermenHistory.addRecollection("0011", new Recollection(
                "00aa", new BigDecimal("30.5"), ZonedDateTime.now().minusDays(10), jpeg
        ));

        mvc.perform(get("/fishermen?days=5"))
                .andExpect(jsonPath("$[0].amount").value("0"));
    }

    @Test
    void index_firstPagination_tooFewData() throws Exception {
        fishermenHistory.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);
        fishermenHistory.addRecollection("0011", new Recollection(
                "00aa", new BigDecimal("30.5"), ZonedDateTime.now(), jpeg
        ));

        mvc.perform(get("/fishermen?size=5&page=1"))
                .andExpect(jsonPath("$.size()").value("1"));
    }

    @Test
    void index_firstPagination_enoughData() throws Exception {
        IntStream.rangeClosed(1, 8).forEach(it -> {
            fishermenHistory.addFisherman(
                    String.format("000%s", it),
                    "Mario",
                    String.format("Paulo %s-th", it),
                    jpeg
            );
        });

        mvc.perform(get("/fishermen?size=5&page=1"))
                .andExpect(jsonPath("$.size()").value("5"));
    }

    @Test
    void index_nextPagination_fewData() throws Exception {
        IntStream.rangeClosed(1, 8).forEach(it -> {
            fishermenHistory.addFisherman(
                    String.format("000%s", it),
                    "Mario",
                    String.format("Paulo %s-th", it),
                    jpeg
            );
        });

        mvc.perform(get("/fishermen?size=5&page=2"))
                .andExpect(jsonPath("$.size()").value("3"));
    }

    @Test
    void detail_noData() throws Exception {
        mvc.perform(get("/fishermen/0011"))
                .andExpect(status().isNotFound());
    }

    @Test
    void detail_contentMapping() throws Exception {
        fishermenHistory.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);

        mvc.perform(get("/fishermen/0011"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void detail_viewData() throws Exception {
        fishermenHistory.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);

        mvc.perform(get("/fishermen/0011"))
                .andExpect(jsonPath("$.id").value("0011"))
                .andExpect(jsonPath("$.name").value("Fabrizio Benvenuto"))
                .andExpect(jsonPath("$.picture").value(jpeg.toString()))
                .andExpect(jsonPath("$.recollections.size()").value(0));
    }

    @Test
    void detail_recollections() throws Exception {
        fishermenHistory.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);
        fishermenHistory.addRecollection("0011", new Recollection(
                "00aa", new BigDecimal("30.5"), ZonedDateTime.now().minusDays(1), jpeg
        ));
        fishermenHistory.addRecollection("0011", new Recollection(
                "00bb", new BigDecimal("10.0"), ZonedDateTime.now().minusDays(2), jpeg
        ));

        mvc.perform(get("/fishermen/0011"))
                .andExpect(jsonPath("$.id").value("0011"))
                .andExpect(jsonPath("$.recollections[0].amount").value("30.5"))
                .andExpect(jsonPath("$.recollections[0].date").exists())
                .andExpect(jsonPath("$.recollections[1].amount").value("10.0"))
                .andExpect(jsonPath("$.recollections[1].date").exists());
    }

    @Test
    void detail_lastRecollections() throws Exception {
        fishermenHistory.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);
        fishermenHistory.addRecollection("0011", new Recollection(
                "00aa", new BigDecimal("30.5"), ZonedDateTime.now(), jpeg
        ));
        fishermenHistory.addRecollection("0011", new Recollection(
                "00bb", new BigDecimal("10.0"), ZonedDateTime.now(), jpeg
        ));

        mvc.perform(get("/fishermen/0011?size=1"))
                .andExpect(jsonPath("$.recollections.size()").value(1));
    }
}