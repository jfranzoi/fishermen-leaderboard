package my.projects.fishermenleaderboard.api.web;

import my.projects.fishermenleaderboard.api.domain.Fisherman;
import my.projects.fishermenleaderboard.api.domain.FishermenHistory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fishermen")
public class LeaderboardController {

    private FishermenHistory fishermenHistory;

    public LeaderboardController(FishermenHistory fishermenHistory) {
        this.fishermenHistory = fishermenHistory;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<FishermenView> index() {
        return fishermenHistory.collect().stream().limit(5)
                .map(it -> toFisherman(it))
                .collect(Collectors.toList());
    }

    private FishermenView toFisherman(Fisherman fisherman) {
        FishermenView result = new FishermenView();
        result.setId(fisherman.id());
        result.setAmount(fisherman.amount());
        return result;
    }

}
