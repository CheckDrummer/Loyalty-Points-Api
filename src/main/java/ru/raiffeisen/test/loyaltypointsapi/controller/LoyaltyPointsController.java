package ru.raiffeisen.test.loyaltypointsapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.test.loyaltypointsapi.model.LoyaltyPoints;
import ru.raiffeisen.test.loyaltypointsapi.service.CurrentPointsService;
import ru.raiffeisen.test.loyaltypointsapi.service.LoyaltyPointsService;
import java.util.List;

@RestController
@AllArgsConstructor
public class LoyaltyPointsController {

    public static final String URL = "/points";

    private LoyaltyPointsService loyaltyPointsService;
    private CurrentPointsService currentPointsService;

    @GetMapping("{userId}" + URL + "/current")
    public Integer getCurrentPoints(@PathVariable Integer userId) {
        return currentPointsService.getCurrentPoints(userId);
    }

    @GetMapping("{userId}" + URL + "/history")
    public List<LoyaltyPoints> getPointsHistory(@PathVariable Integer userId) {
        return loyaltyPointsService.getPointsHistory(userId);
    }

    @GetMapping("{userId}" + URL + "/pending")
    public Integer getPendingPoints(@PathVariable Integer userId) {
        return loyaltyPointsService.getPendingPoints(userId);
    }

    //ToDo Delete
    //I have left that for case if you will test it
    @Deprecated
    @GetMapping(URL + "/test")
    public void testStartOfPointsCalculation() {
        currentPointsService.calculateCurrentPoints();
    }
}
