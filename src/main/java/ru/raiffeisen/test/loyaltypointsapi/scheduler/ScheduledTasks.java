package ru.raiffeisen.test.loyaltypointsapi.scheduler;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.raiffeisen.test.loyaltypointsapi.service.LoyaltyPointsService;

import java.time.ZonedDateTime;


@Component
@AllArgsConstructor
public class ScheduledTasks {

    private LoyaltyPointsService loyaltyPointsService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(cron = "0 15 0 ? * MON") //start every Monday at 00:15:00
    public void reportCurrentTime() {
        log.info("Start points calculation at: " + ZonedDateTime.now());
        loyaltyPointsService.calculateCurrentPoints(ZonedDateTime.now());
        log.info("Stop points calculation at: " + ZonedDateTime.now());
    }
}