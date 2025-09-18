package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class AmountLimitValidator implements BusinessRuleValidator {
    private static final double MAX_LIMIT = 5000.0;

    @Override
    public boolean validate(TransactionContext context) {
        if (context.getAmount() > MAX_LIMIT) {
            System.out.println("Failed: Transaction amount exceeds limit.");
            return false;
        }
        return true;
    }
}

