package ru.raiffeisen.test.loyaltypointsapi.service;

import java.time.ZonedDateTime;

public interface CurrentPointsService {
    void calculateCurrentPoints();
    void calculateCurrentPoints(ZonedDateTime startDateTime);
    Integer getCurrentPoints(Integer userId);
}
