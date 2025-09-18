package com.transaction.validator.service;

import com.transaction.validator.model.TransactionContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BusinessRuleEngineServiceTest {

    @Autowired
    private BusinessRuleEngineService ruleEngine;

    private TransactionContext baseContext() {
        return new TransactionContext(
                "5555444433331111",       // cardNumber
                LocalDate.now().plusYears(2), // cardExpiry
                true,                    // activeCard
                100.0,                   // amount
                "USD",                   // currency
                "US",                    // country
                "GROCERY",               // merchantCategory
                "EMPLOYEE",              // role
                25,                      // age
                "10.0.0.1",              // ipAddress
                "DEVICE123",             // deviceId
                "SAFE_MERCHANT"          // merchantName
        );
    }

    @Test
    void transactionShouldPassWithValidContext() {
        TransactionContext context = baseContext();
        assertTrue(ruleEngine.validateTransaction(context), "Transaction should pass all validators");
    }

    @Test
    void shouldFailForBlockedCountry() {
        TransactionContext context = baseContext();
        context = new TransactionContext(
                context.getCardNumber(), context.getCardExpiry(), context.isActiveCard(),
                context.getAmount(), context.getCurrency(), "NK", // blocked country
                context.getMerchantCategory(), context.getRole(), context.getAge(),
                context.getIpAddress(), context.getDeviceId(), context.getMerchantName()
        );
        assertFalse(ruleEngine.validateTransaction(context), "Transaction should fail for blocked country");
    }

    @Test
    void shouldFailForBlockedMerchantCategory() {
        TransactionContext context = baseContext();
        context = new TransactionContext(
                context.getCardNumber(), context.getCardExpiry(), context.isActiveCard(),
                context.getAmount(), context.getCurrency(), context.getCountry(),
                "GAMBLING", // blocked category
                context.getRole(), context.getAge(),
                context.getIpAddress(), context.getDeviceId(), context.getMerchantName()
        );
        assertFalse(ruleEngine.validateTransaction(context), "Transaction should fail for blocked merchant category");
    }

    @Test
    void shouldFailForAmountLimitExceeded() {
        TransactionContext context = baseContext();
        context = new TransactionContext(
                context.getCardNumber(), context.getCardExpiry(), context.isActiveCard(),
                10000.0, // too high
                context.getCurrency(), context.getCountry(), context.getMerchantCategory(),
                context.getRole(), context.getAge(),
                context.getIpAddress(), context.getDeviceId(), context.getMerchantName()
        );
        assertFalse(ruleEngine.validateTransaction(context), "Transaction should fail for exceeding amount limit");
    }

    @Test
    void shouldFailForExpiredCard() {
        TransactionContext context = baseContext();
        context = new TransactionContext(
                context.getCardNumber(), LocalDate.now().minusDays(1), false, // expired & inactive
                context.getAmount(), context.getCurrency(), context.getCountry(), context.getMerchantCategory(),
                context.getRole(), context.getAge(),
                context.getIpAddress(), context.getDeviceId(), context.getMerchantName()
        );
        assertFalse(ruleEngine.validateTransaction(context), "Transaction should fail for expired card");
    }

    @Test
    void shouldFailForBlacklistedCard() {
        TransactionContext context = baseContext();
        context = new TransactionContext(
                "4111111111111111", // blacklisted card
                context.getCardExpiry(), context.isActiveCard(),
                context.getAmount(), context.getCurrency(), context.getCountry(), context.getMerchantCategory(),
                context.getRole(), context.getAge(),
                context.getIpAddress(), context.getDeviceId(), context.getMerchantName()
        );
        assertFalse(ruleEngine.validateTransaction(context), "Transaction should fail for blacklisted card");
    }


    @Test
    void shouldFailForUnderagePurchase() {
        TransactionContext context = new TransactionContext(
                "5555444433331111",
                LocalDate.now().plusYears(2),
                true,
                50.0,
                "USD",
                "US",
                "ALCOHOL", // restricted category
                "EMPLOYEE",
                16, // under 18
                "10.0.0.1",
                "DEVICE123",
                "LIQUOR_STORE"
        );
        assertFalse(ruleEngine.validateTransaction(context), "Transaction should fail for underage alcohol purchase");
    }

    @Test
    void shouldFailForTooManyTransactionsInShortTime() {
        TransactionContext context = baseContext();

        boolean result = true;
        // Simulate 6 quick successive transactions
        for (int i = 0; i < 6; i++) {
            result = ruleEngine.validateTransaction(context);
        }

        assertFalse(result, "Transaction should fail for too many transactions in short time (velocity check)");
    }

    @Test
    void shouldFailForTransactionDuringBlockedHours() {
        Clock fixedClock = Clock.fixed(
                LocalDateTime.of(2025, 1, 1, 2, 30)
                        .atZone(ZoneId.systemDefault())
                        .toInstant(),
                ZoneId.systemDefault()
        );

        TimeWindowValidator validator = new TimeWindowValidator(fixedClock);

        TransactionContext context = baseContext();
        assertFalse(validator.validate(context),
                "Transaction should fail during restricted hours (2AM–4AM)");
    }


}
