package my.projects.fishermenleaderboard.api.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RefreshingStorage {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshingStorage.class);

    @Scheduled(fixedRateString = "${application.schedule.refreshing-storage.seconds}", timeUnit = TimeUnit.SECONDS)
    public void scheduled() {
        LOGGER.info("Rescheduled!");
    }
}
