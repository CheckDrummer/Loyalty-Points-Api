package ru.raiffeisen.test.loyaltypointsapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.raiffeisen.test.loyaltypointsapi.scheduler.ScheduledTasks;

import java.math.BigDecimal;

public class PointsCounter {

    private static final Integer FIRST_PENDING_POINT = 5000;
    private static final Integer SECOND_PENDING_POINT = 7500;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    public static Integer count (BigDecimal inputTransactionValue) {
        try {
            int transactionValue = inputTransactionValue.toBigInteger().intValue();

            if (transactionValue <= FIRST_PENDING_POINT) {
                return transactionValue;
            }

            if (transactionValue > FIRST_PENDING_POINT && transactionValue <= SECOND_PENDING_POINT) {
                int x2Value = transactionValue - FIRST_PENDING_POINT;
                return FIRST_PENDING_POINT + (x2Value * 2);
            }

            if (transactionValue > SECOND_PENDING_POINT) {
                int x3Value = transactionValue - SECOND_PENDING_POINT;
                return FIRST_PENDING_POINT + ((SECOND_PENDING_POINT - FIRST_PENDING_POINT) * 2) + (x3Value * 3);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return 0;
    }
}
