package ru.raiffeisen.test.loyaltypointsapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raiffeisen.test.loyaltypointsapi.exception.NotFoundException;
import ru.raiffeisen.test.loyaltypointsapi.model.LoyaltyPoints;
import ru.raiffeisen.test.loyaltypointsapi.repository.LoyaltyPointsRepository;

import java.time.*;
import java.util.List;

@Service
@AllArgsConstructor
public class LoyaltyPointsServiceImpl implements LoyaltyPointsService {

    private LoyaltyPointsRepository loyaltyPointsRepository;

    private static final String ADD = "ADD";
    private static final String REMOVE = "REMOVE";

    @Override
    public List<LoyaltyPoints> getPointsHistory(Integer userId) {
        List<LoyaltyPoints> loyaltyPointsOfUser = loyaltyPointsRepository.findAllByUserId(userId);
        if (loyaltyPointsOfUser.isEmpty()) {
            throw new NotFoundException();
        }
        return loyaltyPointsOfUser;
    }

    @Override
    public Integer getPendingPoints(Integer userId) {
        List<LoyaltyPoints> loyaltyPointsOfUser = loyaltyPointsRepository.findAllByUserId(userId);
        if (loyaltyPointsOfUser.isEmpty()) {
            throw new NotFoundException();
        }

        ZonedDateTime startDateTime = ZonedDateTime.of(LocalDate.now(), LocalTime.MIN, ZoneId.of("Europe/Moscow")).with(DayOfWeek.MONDAY);

        int pendingLoyaltyPointsAdd = loyaltyPointsOfUser.stream()
                .filter(point -> point.getOperationDateTime().isAfter(startDateTime))
                .filter(point -> point.getOperationType().equals(ADD))
                .mapToInt(LoyaltyPoints::getValue)
                .sum();

        int pendingLoyaltyPointsRemove = loyaltyPointsOfUser.stream()
                .filter(point -> point.getOperationDateTime().isAfter(startDateTime))
                .filter(point -> point.getOperationType().equals(REMOVE))
                .mapToInt(LoyaltyPoints::getValue)
                .sum();

        return pendingLoyaltyPointsAdd - pendingLoyaltyPointsRemove;
    }
}
