package ru.raiffeisen.test.loyaltypointsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.test.loyaltypointsapi.model.MoneyTransactions;

import java.util.List;

@Repository
public interface MoneyTransactionsRepository extends JpaRepository<MoneyTransactions, Integer> {
    List<MoneyTransactions> findAllByUserId(Integer userId);
}