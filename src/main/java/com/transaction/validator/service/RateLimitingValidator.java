package com.transaction.validator.service;


import com.transaction.validator.model.TransactionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(7)
public class RateLimitingValidator implements BusinessRuleValidator {
    private final Map<String, List<Long>> history = new HashMap<>();

    @Override
    public boolean validate(TransactionContext context) {
        String card = context.getCardNumber();
        long now = System.currentTimeMillis();

        history.putIfAbsent(card, new ArrayList<>());
        List<Long> times = history.get(card);

        times.removeIf(t -> now - t > 60000); // keep last 60s

        if (times.size() >= 5) {
            System.out.println("Failed: Too many transactions in short time.");
            return false;
        }

        times.add(now);
        return true;
    }
}

