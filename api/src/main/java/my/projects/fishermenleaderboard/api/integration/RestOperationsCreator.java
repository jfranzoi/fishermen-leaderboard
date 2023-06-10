package my.projects.fishermenleaderboard.api.integration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class RestOperationsCreator {
    private String rootUri;
    private String apiKey;

    public RestOperationsCreator(String rootUri, String apiKey) {
        this.rootUri = rootUri;
        this.apiKey = apiKey;
    }

    public RestTemplate create() {
        return new RestTemplateBuilder()
                .rootUri(rootUri)
                .additionalInterceptors((request, body, execution) -> {
                    request.getHeaders().add("Authorization", String.format("Bearer %s", apiKey));
                    return execution.execute(request, body);
                })
                .build();
    }
}
