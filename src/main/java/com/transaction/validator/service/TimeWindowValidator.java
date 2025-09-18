package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalTime;

@Component
@Order(8)
public class TimeWindowValidator implements BusinessRuleValidator {
    private final Clock clock;

    public TimeWindowValidator(Clock clock) {
        this.clock = clock;
    }

    @Override
    public boolean validate(TransactionContext context) {
        int hour = LocalTime.now(clock).getHour();
        if (hour >= 2 && hour < 4) {
            System.out.println("Failed: Transactions blocked 2AM–4AM.");
            return false;
        }
        return true;
    }
}


