package com.example.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Saves roster to file every 15 minutes.
 */
@Component
public class RosterPersistenceSchedule {

    private static final Logger logger = LoggerFactory.getLogger(RosterPersistenceSchedule.class);

    @Scheduled(fixedRate = 15 * 60 * 1000) // 15 minutes
    public void saveRosterToFile() {
        logger.debug("Scheduled roster save starting");
        RestServiceApplication.saveRostersToFile();
    }
}
