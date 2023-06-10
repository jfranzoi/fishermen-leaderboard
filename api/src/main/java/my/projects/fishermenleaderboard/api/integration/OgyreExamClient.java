package my.projects.fishermenleaderboard.api.integration;

import org.springframework.web.client.RestOperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OgyreExamClient {
    private RestOperations restOperations;

    public OgyreExamClient(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public List<OgyreFishermen> fishermen() {
        return Arrays.stream(restOperations.getForEntity("/fishermen", OgyreFishermen[].class).getBody())
                .collect(Collectors.toList());
    }

}
