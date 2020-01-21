package ru.raiffeisen.test.loyaltypointsapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.test.loyaltypointsapi.model.LoyaltyPoints;
import ru.raiffeisen.test.loyaltypointsapi.service.LoyaltyPointsService;
import java.util.List;

@RestController
@AllArgsConstructor
public class LoyaltyPointsController {

    public static final String URL = "/points";

    private LoyaltyPointsService loyaltyPointsService;

    @GetMapping(URL + "/current")
    public Integer getCurrentPoints(@RequestParam(name = "userId") Integer userId) {
        return loyaltyPointsService.getCurrentPoints(userId);
    }

    @GetMapping(URL + "/history")
    public List<LoyaltyPoints> getPointsHistory(@RequestParam(name = "userId") Integer userId) {
        return loyaltyPointsService.getPointsHistory(userId);
    }

    @GetMapping(URL + "/pending")
    public Integer getPendingPoints(@RequestParam(name = "userId") Integer userId) {
        return loyaltyPointsService.getPendingPoints(userId);
    }

    //ToDo Delete
    //I have left that for case if you will test it
    @Deprecated
    @GetMapping(URL + "/test")
    public void testStartOfPointsCalculation() {
        loyaltyPointsService.calculateCurrentPoints();
    }
}
