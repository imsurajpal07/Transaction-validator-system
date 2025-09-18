package com.transaction.validator.model;


import java.time.LocalDate;

public class TransactionContext {
    private String cardNumber;
    private LocalDate cardExpiry;
    private boolean activeCard;
    private double amount;
    private String currency;
    private String country;
    private String merchantCategory;
    private String role;
    private int age;
    private String ipAddress;
    private String deviceId;
    private String merchantName;

    public TransactionContext(String cardNumber, LocalDate cardExpiry, boolean activeCard,
                              double amount, String currency, String country,
                              String merchantCategory, String role, int age,
                              String ipAddress, String deviceId, String merchantName) {
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.activeCard = activeCard;
        this.amount = amount;
        this.currency = currency;
        this.country = country;
        this.merchantCategory = merchantCategory;
        this.role = role;
        this.age = age;
        this.ipAddress = ipAddress;
        this.deviceId = deviceId;
        this.merchantName = merchantName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(LocalDate cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public boolean isActiveCard() {
        return activeCard;
    }

    public void setActiveCard(boolean activeCard) {
        this.activeCard = activeCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMerchantCategory() {
        return merchantCategory;
    }

    public void setMerchantCategory(String merchantCategory) {
        this.merchantCategory = merchantCategory;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
