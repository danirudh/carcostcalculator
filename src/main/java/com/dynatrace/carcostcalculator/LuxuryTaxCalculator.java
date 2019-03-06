package com.dynatrace.carcostcalculator;

import org.springframework.stereotype.Component;

@Component
public class LuxuryTaxCalculator {

    public double calculate(double carCost) {
        if (carCost > 30000 && carCost < 60000) {
            return 850;
        } else if (carCost > 60000) {
            return 1000;
        }
        return 0;
    }
}
