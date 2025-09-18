package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(2)
public class MerchantCategoryValidator implements BusinessRuleValidator {
    private static final Set<String> BLOCKED_CATEGORIES = Set.of("GAMBLING", "ADULT");

    @Override
    public boolean validate(TransactionContext context) {
        if (BLOCKED_CATEGORIES.contains(context.getMerchantCategory())) {
            System.out.println("Failed: Merchant category not allowed.");
            return false;
        }
        return true;
    }
}

