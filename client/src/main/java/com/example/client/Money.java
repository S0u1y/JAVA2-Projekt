package com.example.client;

import javax.money.*;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import java.util.Locale;

public class Money {
    public static CurrencyUnit czk = Monetary.getCurrency(Locale.of("cs", "CZ"));

    public static CurrencyUnit usd = Monetary.getCurrency(Locale.of("en", "US"));

    public static CurrencyUnit ru = Monetary.getCurrency(Locale.of("ru", "RU"));

    private MonetaryAmount amount;

    Money(String base, double amount){
        this.amount = Monetary.getDefaultAmountFactory()
                .setNumber(amount)
                .setCurrency(base)
                .create();
    }

    Money(CurrencyUnit unit, double amount){
        this.amount = Monetary.getDefaultAmountFactory()
                .setNumber(amount)
                .setCurrency(unit)
                .create();
    }

    MonetaryAmount convert(String convertTo){
        CurrencyConversion conversion = MonetaryConversions.getConversion(Monetary.getCurrency(Locale.of(convertTo)));
        return amount.with(conversion);
    }

    MonetaryAmount convert(Locale locale){
        CurrencyConversion conversion = MonetaryConversions.getConversion(Monetary.getCurrency(locale));
        return amount.with(conversion);
    }

    public MonetaryAmount getAmount(){
        return this.amount;
    }

}
