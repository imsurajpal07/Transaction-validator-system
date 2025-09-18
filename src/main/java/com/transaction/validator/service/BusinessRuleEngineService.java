package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessRuleEngineService {
    private final List<BusinessRuleValidator> validators;

    public BusinessRuleEngineService(List<BusinessRuleValidator> validators) {
        this.validators = validators;
    }

    public boolean validateTransaction(TransactionContext context) {
        for (BusinessRuleValidator v : validators) {
            if (!v.validate(context)) {
                return false; // Stop at first failure
            }
        }
        System.out.println("Transaction approved.");
        return true;
    }
}
