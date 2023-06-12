package my.projects.fishermenleaderboard.api.web;

import my.projects.fishermenleaderboard.api.domain.Fisherman;
import my.projects.fishermenleaderboard.api.domain.FishermenHistory;
import my.projects.fishermenleaderboard.api.web.dto.FishermanDetailsView;
import my.projects.fishermenleaderboard.api.web.dto.FishermenView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fishermen")
public class LeaderboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaderboardController.class);

    private FishermenHistory fishermenHistory;

    public LeaderboardController(FishermenHistory fishermenHistory) {
        this.fishermenHistory = fishermenHistory;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<FishermenView> index(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "days", required = false, defaultValue = "5") int days
    ) {
        Pagination pagination = new Pagination(size, page);
        Period period = Period.ofDays(days);
        LOGGER.info("Collecting, period: [{}], pagination: [{}]", period, pagination);

        return fishermenHistory.collect(period).stream()
                .skip(pagination.from()).limit(pagination.to())
                .map(it -> toFisherman(it, period))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @ResponseBody
    public ResponseEntity<FishermanDetailsView> detail(@PathVariable(value = "id") String id) {
        LOGGER.info("Details, id: [{}]", id);

        return fishermenHistory.by(id)
                .map(x -> ResponseEntity.ok(toDetails(x)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private FishermanDetailsView toDetails(Fisherman fisherman) {
        FishermanDetailsView result = new FishermanDetailsView();
        result.setId(fisherman.id());
        result.setName(fisherman.name());
        result.setPicture(fisherman.picture());
        return result;
    }

    private FishermenView toFisherman(Fisherman fisherman, Period period) {
        FishermenView result = new FishermenView();
        result.setId(fisherman.id());
        result.setName(fisherman.name());
        result.setAmount(fisherman.amountIn(period));
        result.setPicture(fisherman.picture());
        return result;
    }

}
