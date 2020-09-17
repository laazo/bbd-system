package com.azola.bbdsystem.util;

import com.azola.bbdsystem.entity.CurrencyConversionRate;

import java.math.BigDecimal;

public class ConversionHelper {

    public static BigDecimal convertToRand(BigDecimal amount, CurrencyConversionRate rate) {
        BigDecimal result = BigDecimal.ZERO;
        switch (rate.getConversionIndicator()) {
            case "/":
                result = amount.divide(rate.getRate());
                break;
            case "*":
                result = amount.multiply(rate.getRate());
        }
       return result;
    }
}
