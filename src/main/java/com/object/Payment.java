package com.object;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class Payment {

    private final String orderId = UUID.randomUUID().toString();
    private final String currency;
    private final Double foreignCurrencyAmount;
    private final Double exRate;
    private final Double convertedAmount;

    public Payment(String currency, Double foreignCurrencyAmount, Double exRate, Double convertedAmount) {
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
    }
}
