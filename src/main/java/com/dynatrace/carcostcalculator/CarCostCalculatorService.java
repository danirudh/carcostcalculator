package com.dynatrace.carcostcalculator;

import static org.springframework.util.StringUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarCostCalculatorService {

    @Autowired
    private CarTypeCostCalculator carTypeCostCalculator;
    @Autowired
    private OptionCostCalculator optionCostCalculator;
    @Autowired
    private LuxuryTaxCalculator luxuryTaxCalculator;
    @Autowired
    private GasGuzzlerTaxCalculator gasGuzzlerTaxCalculator;

    public double calculate(String carType, String[] options, String destinationZip) throws CarCostCalculationException {
        if (isEmpty(carType) || options == null || options.length == 0) {
            return 0;
        }

        double carCost = 0.0;

        carCost += carTypeCostCalculator.calculate(carType);

        for (String option : options) {
            carCost += optionCostCalculator.calculate(carType, option);
        }

        carCost += luxuryTaxCalculator.calculate(carCost);
        carCost += gasGuzzlerTaxCalculator.calculate(carType, destinationZip);

        return carCost;
    }
}
