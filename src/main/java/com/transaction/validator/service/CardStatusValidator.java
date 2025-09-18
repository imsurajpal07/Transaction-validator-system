package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(6)
public class CardStatusValidator implements BusinessRuleValidator {
    @Override
    public boolean validate(TransactionContext context) {
        if (context.getCardExpiry().isBefore(LocalDate.now()) || !context.isActiveCard()) {
            System.out.println("Failed: Card inactive or expired.");
            return false;
        }
        return true;
    }
}

