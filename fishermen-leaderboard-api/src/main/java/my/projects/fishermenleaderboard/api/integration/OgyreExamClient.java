package my.projects.fishermenleaderboard.api.integration;

import my.projects.fishermenleaderboard.api.integration.dto.OgyreFishermen;
import my.projects.fishermenleaderboard.api.integration.dto.OgyreRecollection;
import my.projects.fishermenleaderboard.api.integration.dto.OgyreRecollectionsResponse;
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

    public List<OgyreRecollection> recollections(String fisherman) {
        return restOperations.getForEntity("/recollections/{fisherman}", OgyreRecollectionsResponse.class, fisherman)
                .getBody()
                .listOfrecollections;
    }
}
