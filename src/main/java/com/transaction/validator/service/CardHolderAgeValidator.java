package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(11)
public class CardHolderAgeValidator implements BusinessRuleValidator {
    @Override
    public boolean validate(TransactionContext context) {
        if (context.getAge() < 18 && "ALCOHOL".equalsIgnoreCase(context.getMerchantCategory())) {
            System.out.println("Failed: Underage purchase restriction.");
            return false;
        }
        return true;
    }
}
