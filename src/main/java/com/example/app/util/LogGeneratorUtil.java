package com.example.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class LogGeneratorUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogGeneratorUtil.class);
    private static final Random RANDOM = new Random();
    private static final String[] INFO_MESSAGES = {
            "User successfully logged in.",
            "Background job completed successfully.",
            "Health check passed.",
            "Configuration reloaded.",
            "Cache cleared successfully."
    };

    private static final String[] DEBUG_MESSAGES = {
            "Debugging cache miss for key: session-12345",
            "Fetching user details from database.",
            "Request processed in 102ms.",
            "Validating input for user registration.",
            "Starting background job for cleanup."
    };

    private static final String[] ERROR_MESSAGES = {
            "Database connection timeout occurred.",
            "Null pointer exception encountered in processing.",
            "User authentication failed due to invalid token.",
            "Error while reading configuration file.",
            "Cache synchronization failed."
    };

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void startLogGeneration() {
        scheduler.scheduleAtFixedRate(this::generateRandomLog, 0, 5, TimeUnit.SECONDS);
    }

    private void generateRandomLog() {
        int logType = RANDOM.nextInt(3); // 0: INFO, 1: DEBUG, 2: ERROR

        switch (logType) {
            case 0:
                LOGGER.info(getRandomMessage(INFO_MESSAGES));
                break;
            case 1:
                LOGGER.debug(getRandomMessage(DEBUG_MESSAGES));
                break;
            case 2:
                LOGGER.error(getRandomMessage(ERROR_MESSAGES));
                break;
            default:
                LOGGER.warn("Unexpected log type.");
        }
    }

    private String getRandomMessage(String[] messages) {
        return messages[RANDOM.nextInt(messages.length)];
    }
}
