package ru.raiffeisen.test.loyaltypointsapi.service;

import ru.raiffeisen.test.loyaltypointsapi.model.LoyaltyPoints;

import java.util.List;

public interface LoyaltyPointsService {
    Integer getPendingPoints(Integer userId);
    List<LoyaltyPoints> getPointsHistory(Integer userId);
}
