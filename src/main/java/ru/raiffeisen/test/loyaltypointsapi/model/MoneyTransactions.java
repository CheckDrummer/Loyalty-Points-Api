package ru.raiffeisen.test.loyaltypointsapi.model;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Entity
public class MoneyTransactions {
    @Id
    private Integer id;
    private BigDecimal value;
    private ZonedDateTime transactionDateTime;
    private String operationType;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public ZonedDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(ZonedDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
