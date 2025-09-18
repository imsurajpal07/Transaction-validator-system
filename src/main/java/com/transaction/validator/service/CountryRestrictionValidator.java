package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(1)
public class CountryRestrictionValidator implements BusinessRuleValidator {
    private static final Set<String> BLOCKED_COUNTRIES = Set.of("NK", "IR", "SY");

    @Override
    public boolean validate(TransactionContext context) {
        if (BLOCKED_COUNTRIES.contains(context.getCountry())) {
            System.out.println("Failed: Country not allowed.");
            return false;
        }
        return true;
    }
}

