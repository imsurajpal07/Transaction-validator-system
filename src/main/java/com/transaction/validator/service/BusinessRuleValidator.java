package com.transaction.validator.service;


import com.transaction.validator.model.TransactionContext;

public interface BusinessRuleValidator {
    boolean validate(TransactionContext context);
}

