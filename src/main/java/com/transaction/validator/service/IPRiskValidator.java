package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(10)
public class IPRiskValidator implements BusinessRuleValidator {
    private static final Set<String> BLOCKED_IPS = Set.of("192.168.1.100");

    @Override
    public boolean validate(TransactionContext context) {
        if (BLOCKED_IPS.contains(context.getIpAddress())) {
            System.out.println("Failed: Risky IP detected.");
            return false;
        }
        return true;
    }
}
