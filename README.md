***

# Transaction Validator System

A robust, extensible Java Spring Boot application designed for validating financial transactions using a configurable set of business rules. This project demonstrates modular rule-based validation using modern Java and Spring concepts.

## Features

- **Pluggable validators**: Easily add or modify business rules as standalone validator classes.
- **Central rule engine**: Transactions are validated against a composite of all rule validators.
- **Spring Boot architecture**: Production-ready setup for web applications and microservices.
- **Domain-driven design**: Clear separation of concerns with models, services, and config.
- **Comprehensive unit tests**: JUnit 5 test suite covering all major validation scenarios.

## Project Structure

```
src/
  main/
    java/com/transaction/validator/
      Config/           # Spring config beans (e.g., AppConfig)
      model/            # Data model (e.g., TransactionContext)
      service/          # Rule validators & engine services
        - AmountLimitValidator.java
        - BusinessRuleEngineService.java
        - ...
      ValidatorApplication.java   # Main Spring Boot entry point
    resources/
  test/
    java/com/transaction/validator/
      service/          # Test cases for BusinessRuleEngineService, etc.
pom.xml                 # Maven build configuration
```

## How It Works

1. **TransactionContext**: Encapsulates all transaction attributes (amount, card info, country, merchant, etc.).
2. **BusinessRuleValidator**: Interface for each business rule (e.g., amount limit, card status, country/merchant restrictions, time window).
3. **BusinessRuleEngineService**: Iterates over all registered validators—rejects transaction at first failure.
4. **Configuration**: Validators are registered as Spring Beans for automatic injection.

## Example Validators

- `AmountLimitValidator`: Rejects transactions over a threshold.
- `BlacklistCardValidator`: Blocks blacklisted card numbers.
- `CountryRestrictionValidator`: Disallows transactions from blocked countries.
- `CardStatusValidator`: Validates that the card is active & not expired.
- `MerchantCategoryValidator`: Checks for restricted merchant categories.

## Example Usage

You can quickly test validation logic using the included JUnit tests. Example scenario in the test suite:

- Validates a transaction with normal data — **should pass**.
- Tries transaction with expired card, blocked country, excessive amount, etc. — **should fail** for each rule.

## Customization

To add or change rules:
- Create a new class in `service/` implementing `BusinessRuleValidator`.
- Register it as a Spring `@Component`.
- It will be picked up and executed automatically for every transaction.
