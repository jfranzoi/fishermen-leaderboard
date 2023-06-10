package my.projects.fishermenleaderboard.api.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class FishermenHistory {

    private List<Fisherman> fishermen = new ArrayList<>();

    public void addFisherman(String id) {
        fishermen.add(new Fisherman(id));
    }

    public void onRecollection(String fisherman, BigDecimal amount) {
        fishermen.stream().filter(it -> fisherman.equals(it.id()))
                .forEach(it -> it.addRecollection(amount));
    }

    public List<Fisherman> collect() {
        return fishermen;
    }
}
