package com.example.client;

import org.javamoney.moneta.FastMoney;

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

        MonetaryAmount output = amount;

        try{

            output = amount.with(conversion);

        }catch (Exception e){

            if(locale.getCountry().equals("ru") || locale.getCountry().equals("RU")){

                output = Monetary.getDefaultAmountFactory()
                        .setNumber(amount.getNumber())
                        .setCurrency("RUB")
                        .create();

                //conversion rates as of 6.5.2023
                if(amount.getCurrency().getCurrencyCode().equals("CZK")){
                    output = output.multiply(3.67);
                }
                if(amount.getCurrency().getCurrencyCode().equals("USD")){
                    output = output.multiply(77.82);
                }
            }

        }

        return output;
    }

    public MonetaryAmount getAmount(){
        return this.amount;
    }

}
