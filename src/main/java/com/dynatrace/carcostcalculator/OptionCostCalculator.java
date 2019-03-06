package com.dynatrace.carcostcalculator;

import static com.dynatrace.carcostcalculator.CarTypeConstants.COUPE;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dynatrace.carcostcalculator.repository.OptionCostRepository;

@Component
public class OptionCostCalculator {

    private static final String TOW_PACKAGE = "TOWPACKAGE";

    @Autowired
    private OptionCostRepository optionCostRepository;

    public double calculate(String carType, String option) throws CarCostCalculationException {
        if (COUPE.equalsIgnoreCase(carType) && TOW_PACKAGE.equalsIgnoreCase(option)) {
            throw new CarCostCalculationException("Tow package option not available for coupe.");
        }

        Map<String, Double> options = optionCostRepository.getOptionsCost(carType.toUpperCase());

        if (isEmpty(options) || !options.containsKey(option.toUpperCase())) {
            return 0;
        }

        return options.get(option.toUpperCase());
    }
}
