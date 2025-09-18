package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(9)
public class BlacklistCardValidator implements BusinessRuleValidator {
    private static final Set<String> BLACKLIST = Set.of("4111111111111111");

    @Override
    public boolean validate(TransactionContext context) {
        if (BLACKLIST.contains(context.getCardNumber())) {
            System.out.println("Failed: Card is blacklisted.");
            return false;
        }
        return true;
    }
}

