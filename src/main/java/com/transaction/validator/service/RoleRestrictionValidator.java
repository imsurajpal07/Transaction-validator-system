package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class RoleRestrictionValidator implements BusinessRuleValidator {
    @Override
    public boolean validate(TransactionContext context) {
        if ("INTERN".equalsIgnoreCase(context.getRole()) &&
                "TRAVEL".equalsIgnoreCase(context.getMerchantCategory())) {
            System.out.println("Failed: Interns cannot spend on travel.");
            return false;
        }
        return true;
    }
}
