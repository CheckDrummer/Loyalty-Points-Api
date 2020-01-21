package ru.raiffeisen.test.loyaltypointsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.test.loyaltypointsapi.model.LoyaltyPoints;

import java.util.List;

@Repository
public interface LoyaltyPointsRepository extends JpaRepository<LoyaltyPoints, Integer> {
    List<LoyaltyPoints> findAllByUserId(Integer userId);
}