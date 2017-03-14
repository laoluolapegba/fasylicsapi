package com.fasyl.entity;

public class Price {
    private double pricePerMessage;
    private String currency;
    public double getPricePerMessage() {
        return pricePerMessage;
    }

    public void setPricePerMessage(double pricePerMessage) {
        this.pricePerMessage = pricePerMessage;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}