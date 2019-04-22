package com.dynatrace.carcostcalculator;

import static org.springframework.util.StringUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarCostCalculatorService {

    @Autowired
    private CarTypeCostCalculator carTypeCostCalculator;
    @Autowired
    private OptionsCostCalculator optionsCostCalculator;
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

        carCost += optionsCostCalculator.calculate(carType, options);

        carCost += luxuryTaxCalculator.calculate(carCost);
        carCost += gasGuzzlerTaxCalculator.calculate(carType, destinationZip);

        return carCost;
    }
}
