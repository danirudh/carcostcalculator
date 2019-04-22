package com.dynatrace.carcostcalculator;

import static com.dynatrace.carcostcalculator.CarTypeConstants.COUPE;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dynatrace.carcostcalculator.repository.OptionCostRepository;

@Component
public class OptionsCostCalculator {

    private static final String TOW_PACKAGE = "TOWPACKAGE";

    @Autowired
    private OptionCostRepository optionCostRepository;

    public double calculate(String carType, String[] options) throws CarCostCalculationException {
        Map<String, Double> availableOptions = optionCostRepository.getOptionsCost(carType.toUpperCase());

        if (isEmpty(availableOptions)) {
            return 0;
        }

        double optionsCost = 0;

        for (String option : options) {
            if (COUPE.equalsIgnoreCase(carType) && TOW_PACKAGE.equalsIgnoreCase(option)) {
                throw new CarCostCalculationException("Tow package option not available for coupe.");
            }

            if (availableOptions.containsKey(option.toUpperCase())) {
                optionsCost += availableOptions.get(option.toUpperCase());
            }
        }

        return optionsCost;
    }
}
