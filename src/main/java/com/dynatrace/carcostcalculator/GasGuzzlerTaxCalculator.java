package com.dynatrace.carcostcalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dynatrace.carcostcalculator.repository.GasGuzzlerRepository;

@Component
public class GasGuzzlerTaxCalculator {

    @Autowired
    private GasGuzzlerRepository gasGuzzlerRepository;

    public double calculate(String carType, String destinationZip) {
        double gasGuzzlerTax = 0;

        if (isGasGuzzler(carType)) {
            gasGuzzlerTax += slowTaxCalculationMethod(destinationZip);
            gasGuzzlerTax += 1000;
        }

        return gasGuzzlerTax;
    }

    private boolean isGasGuzzler(String carType) {
        String[] gasGuzzlers = gasGuzzlerRepository.get();
        for (String gasGuzzler : gasGuzzlers) {
            if (gasGuzzler.equalsIgnoreCase(carType)) {
                return true;
            }
        }
        return false;
    }

    private double slowTaxCalculationMethod(String destinationZip) {
        // the Thread.sleep cannot be removed
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            // Do nothing
        }
        return 500;
    }
}
