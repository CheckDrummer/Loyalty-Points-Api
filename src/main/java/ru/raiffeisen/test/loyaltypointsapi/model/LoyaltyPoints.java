package ru.raiffeisen.test.loyaltypointsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Data
@Entity
public class LoyaltyPoints {
    @Id
    @JsonIgnore
    private Integer id;
    private Integer value;
    @JsonIgnore
    private ZonedDateTime operationDateTime;
    private String operationType;
    @JsonIgnore
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ZonedDateTime getOperationDateTime() {
        return operationDateTime;
    }

    public void setOperationDateTime(ZonedDateTime operationDateTime) {
        this.operationDateTime = operationDateTime;
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
