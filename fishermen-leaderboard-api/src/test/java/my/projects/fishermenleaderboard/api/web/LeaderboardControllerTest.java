package my.projects.fishermenleaderboard.api.web;

import my.projects.fishermenleaderboard.api.domain.FishermenHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LeaderboardControllerTest {
    private MockMvc mvc;
    private FishermenHistory fishermenHistory;

    @BeforeEach
    void setUp() {
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
    void noData() throws Exception {
        mvc.perform(get("/fishermen"))
                .andExpect(jsonPath("$.size()").value("0"));
    }

    @Test
    void firstPagination_tooFewData() throws Exception {
        fishermenHistory.addFisherman("0011", "Fabrizio", "Benvenuto");
        fishermenHistory.onRecollection("0011", new BigDecimal("30.5"));

        mvc.perform(get("/fishermen"))
                .andExpect(jsonPath("$.size()").value("1"))
                .andExpect(jsonPath("$[0].id").value("0011"))
                .andExpect(jsonPath("$[0].name").value("Fabrizio Benvenuto"))
                .andExpect(jsonPath("$[0].amount").value("30.5"));
    }

    @Test
    void firstPagination_enoughData() throws Exception {
        IntStream.rangeClosed(1, 8).forEach(it -> {
            fishermenHistory.addFisherman(
                    String.format("000%s", it),
                    "Mario",
                    String.format("Paulo %s-th", it)
            );
        });

        mvc.perform(get("/fishermen"))
                .andExpect(jsonPath("$.size()").value("5"));
    }

    @Test
    void nextPagination_fewData() throws Exception {
        IntStream.rangeClosed(1, 8).forEach(it -> {
            fishermenHistory.addFisherman(
                    String.format("000%s", it),
                    "Mario",
                    String.format("Paulo %s-th", it)
            );
        });

        mvc.perform(get("/fishermen?from=5"))
                .andExpect(jsonPath("$.size()").value("3"));
    }
}