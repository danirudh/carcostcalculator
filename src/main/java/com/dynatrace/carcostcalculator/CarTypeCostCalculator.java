package com.dynatrace.carcostcalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dynatrace.carcostcalculator.repository.CarTypeCostRepository;

@Component
public class CarTypeCostCalculator {

    @Autowired
    private CarTypeCostRepository carTypeCostRepository;

    public double calculate(String carType) {
        Double carTypeCost = carTypeCostRepository.get(carType.toUpperCase());
        return carTypeCost == null ? 0 : carTypeCost;
    }
}
