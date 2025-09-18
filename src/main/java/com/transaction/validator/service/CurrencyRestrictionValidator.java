package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(5)
public class CurrencyRestrictionValidator implements BusinessRuleValidator {
    private static final Set<String> ALLOWED = Set.of("USD", "EUR", "GBP");

    @Override
    public boolean validate(TransactionContext context) {
        if (!ALLOWED.contains(context.getCurrency())) {
            System.out.println("Failed: Unsupported currency.");
            return false;
        }
        return true;
    }
}
