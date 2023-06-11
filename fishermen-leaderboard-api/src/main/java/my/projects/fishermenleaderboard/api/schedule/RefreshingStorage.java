package my.projects.fishermenleaderboard.api.schedule;

import my.projects.fishermenleaderboard.api.domain.FishermenHistory;
import my.projects.fishermenleaderboard.api.domain.Recollection;
import my.projects.fishermenleaderboard.api.integration.OgyreExamClient;
import my.projects.fishermenleaderboard.api.integration.RestOperationsCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class RefreshingStorage {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshingStorage.class);

    @Value("${application.ogyre.base-uri}")
    private String baseUri;

    @Value("${application.ogyre.api-key}")
    private String apiKey;

    @Autowired
    private FishermenHistory fishermenHistory;

    private OgyreExamClient client;

    @PostConstruct
    public void init() {
        client = new OgyreExamClient(new RestOperationsCreator(baseUri, apiKey).create());
        LOGGER.info("Client created, base URI: {}", baseUri);
    }

    @Scheduled(fixedRateString = "${application.schedule.refreshing-storage.minutes}", timeUnit = TimeUnit.MINUTES)
    public void scheduled() {
        LOGGER.info("Rescheduled!");

        client.fishermen().stream().forEach(fisherman -> {
            fishermenHistory.addFisherman(fisherman._id,
                    fisherman.name, fisherman.surname, fisherman.fishermanPicture
            );

            client.recollections(fisherman._id).stream().forEach(recollection -> {
                fishermenHistory.addRecollection(fisherman._id, new Recollection(
                        recollection.kg, recollection.date, recollection.pictureRecollection
                ));
            });
        });
    }
}
