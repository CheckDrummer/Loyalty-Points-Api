package ru.raiffeisen.test.loyaltypointsapi.service;

import ru.raiffeisen.test.loyaltypointsapi.model.LoyaltyPoints;

import java.time.ZonedDateTime;
import java.util.List;

public interface LoyaltyPointsService {
    Integer getCurrentPoints(Integer userId);
    List<LoyaltyPoints> getPointsHistory(Integer userId);

    Integer getPendingPoints(Integer userId);
    void calculateCurrentPoints();
    void calculateCurrentPoints(ZonedDateTime startDateTime);

}
