package ru.raiffeisen.test.loyaltypointsapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raiffeisen.test.loyaltypointsapi.exception.NotFoundException;
import ru.raiffeisen.test.loyaltypointsapi.model.MoneyTransactions;
import ru.raiffeisen.test.loyaltypointsapi.model.UserData;
import ru.raiffeisen.test.loyaltypointsapi.repository.MoneyTransactionsRepository;
import ru.raiffeisen.test.loyaltypointsapi.repository.UserDataRepository;
import ru.raiffeisen.test.loyaltypointsapi.utils.PointsCounter;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrentPointsServiceImpl implements CurrentPointsService {

    private MoneyTransactionsRepository moneyTransactionsRepository;
    private UserDataRepository userDataRepository;

    private static final Long POINTS_LOSING_PERIOD_IN_WEEKS = 5L;
    private static final Long MINIMUM_MONEY_SPENT_DURIND_A_WEEK = 500L;

    @Override
    public Integer getCurrentPoints(Integer userId) {
        Optional<UserData> userData = userDataRepository.findFirstById(userId);
        if (!userData.isPresent()) {
            throw new NotFoundException();
        }
        return userData.get().getCurrentPoints();
    }

    @Override
    public void calculateCurrentPoints() {
        ZonedDateTime startDateTime = ZonedDateTime.of(LocalDate.now(), LocalTime.MIN, ZoneId.systemDefault()).with(DayOfWeek.MONDAY);
        calculatePoints(startDateTime);
    }

    @Override
    public void calculateCurrentPoints(ZonedDateTime startDateTime) {
        calculatePoints(startDateTime);
    }

    private void calculatePoints(ZonedDateTime startDateTime) {
        List<UserData> allUsers = userDataRepository.findAll();

        for (UserData userData : allUsers) {
            Integer newPoints = calculate(userData.getId(), startDateTime);
            if (newPoints > 0) {
                Integer currentPoints = userData.getCurrentPoints();
                userData.setCurrentPoints(currentPoints + newPoints);
                userDataRepository.save(userData);
            }
        }
    }

    private Integer calculate(Integer userId, ZonedDateTime startDateTime) {
        List<MoneyTransactions> moneyTransactions = moneyTransactionsRepository.findAllByUserId(userId);

        if (moneyTransactions.isEmpty()) {
            throw new NotFoundException();
        }

        if (checkPointsLoss(moneyTransactions, userId, startDateTime)) {
            return 0;
        }

        List<MoneyTransactions> listOfTransactionsDuringWeek = moneyTransactions.stream()
                .filter(transaction -> (transaction.getTransactionDateTime().isAfter(startDateTime.minusWeeks(1))
                        || transaction.getTransactionDateTime().isEqual(startDateTime.minusWeeks(1)))
                )
                .filter(transaction -> transaction.getTransactionDateTime().isBefore(startDateTime))
                .collect(Collectors.toList());

        if (isNoMinMoneySpending(listOfTransactionsDuringWeek)) {
            return 0;
        }

        if (isNoEverydayMoneyTransactions(listOfTransactionsDuringWeek)) {
            return 0;
        }

        Integer points = 0;
        for (MoneyTransactions transaction : listOfTransactionsDuringWeek) {
            points = points + PointsCounter.count(transaction.getValue());
        }
        return points;
    }


    private boolean checkPointsLoss(List<MoneyTransactions> moneyTransactions, Integer userId, ZonedDateTime startDateTime) {
        List<MoneyTransactions> transactionsDuringPeriodOfPointsLost = moneyTransactions.stream()
                .filter(moneyTransaction -> moneyTransaction.getTransactionDateTime()
                        .isAfter(startDateTime
                                .minusWeeks(POINTS_LOSING_PERIOD_IN_WEEKS)
                        )
                )
                .collect(Collectors.toList());

        if (transactionsDuringPeriodOfPointsLost.isEmpty()) {
            Optional<UserData> UserDataOptional = userDataRepository.findFirstById(userId);
            if (UserDataOptional.isPresent()) {
                UserData userData = UserDataOptional.get();
                userData.setCurrentPoints(0);
                userDataRepository.save(userData);
                return true;
            }
        }
        return false;
    }

    private boolean isNoMinMoneySpending(List<MoneyTransactions> listOfTransactionsDuringWeek) {
        BigDecimal moneyDuringWeek = BigDecimal.ZERO;
        for (MoneyTransactions transaction : listOfTransactionsDuringWeek) {
            moneyDuringWeek = moneyDuringWeek.add(transaction.getValue());
        }
        return BigDecimal.valueOf(MINIMUM_MONEY_SPENT_DURIND_A_WEEK).compareTo(moneyDuringWeek) > 0;
    }

    private boolean isNoEverydayMoneyTransactions(List<MoneyTransactions> listOfTransactionsDuringWeek) {
        for (long weekDaysNumeration = 0L; weekDaysNumeration < 7L; weekDaysNumeration++){
            long days = weekDaysNumeration;
            DayOfWeek checkedDayOfWeek = DayOfWeek.MONDAY.plus(days);
            if (listOfTransactionsDuringWeek.stream()
                    .filter(q -> q.getTransactionDateTime().getDayOfWeek().equals(checkedDayOfWeek))
                    .collect(Collectors.toList())
                    .isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
