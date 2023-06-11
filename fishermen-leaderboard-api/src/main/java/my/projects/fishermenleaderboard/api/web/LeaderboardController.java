package my.projects.fishermenleaderboard.api.web;

import my.projects.fishermenleaderboard.api.domain.Fisherman;
import my.projects.fishermenleaderboard.api.domain.FishermenHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        Pagination pagination = new Pagination(size, page);
        LOGGER.info("Collecting, pagination: [{}]", pagination);
        return fishermenHistory.collect().stream().skip(pagination.from()).limit(pagination.to())
                .map(it -> toFisherman(it))
                .collect(Collectors.toList());
    }

    private FishermenView toFisherman(Fisherman fisherman) {
        FishermenView result = new FishermenView();
        result.setId(fisherman.id());
        result.setName(fisherman.name());
        result.setAmount(fisherman.amount());
        return result;
    }

}