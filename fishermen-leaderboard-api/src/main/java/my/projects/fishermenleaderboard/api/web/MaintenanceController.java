package my.projects.fishermenleaderboard.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class MaintenanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaintenanceController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        LOGGER.info("Checking status");
        return "OK";
    }
}
