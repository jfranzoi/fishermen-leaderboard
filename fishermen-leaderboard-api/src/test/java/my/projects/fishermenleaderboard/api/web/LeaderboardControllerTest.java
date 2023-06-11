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
    void contentMapping() throws Exception {
        mvc.perform(get("/fishermen"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void viewData() throws Exception {
        fishermenHistory.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);
        fishermenHistory.addRecollection("0011", new Recollection(new BigDecimal("30.5"), ZonedDateTime.now(), jpeg));

        mvc.perform(get("/fishermen?size=5&page=1"))
                .andExpect(jsonPath("$[0].id").value("0011"))
                .andExpect(jsonPath("$[0].name").value("Fabrizio Benvenuto"))
                .andExpect(jsonPath("$[0].amount").value("30.5"))
                .andExpect(jsonPath("$[0].picture").value(jpeg.toString()));
    }

    @Test
    void noData() throws Exception {
        mvc.perform(get("/fishermen"))
                .andExpect(jsonPath("$.size()").value("0"));
    }

    @Test
    void firstPagination_tooFewData() throws Exception {
        fishermenHistory.addFisherman("0011", "Fabrizio", "Benvenuto", jpeg);
        fishermenHistory.addRecollection("0011", new Recollection(new BigDecimal("30.5"), ZonedDateTime.now(), jpeg));

        mvc.perform(get("/fishermen?size=5&page=1"))
                .andExpect(jsonPath("$.size()").value("1"));
    }

    @Test
    void firstPagination_enoughData() throws Exception {
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
    void nextPagination_fewData() throws Exception {
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
}