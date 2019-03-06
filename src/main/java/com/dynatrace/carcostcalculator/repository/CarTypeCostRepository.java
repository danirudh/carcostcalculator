package com.dynatrace.carcostcalculator.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dynatrace.carcostcalculator.CarTypeConstants;

@Repository
public class CarTypeCostRepository {

    public Double get(String carType) {
        Map<String, Double> carTypeCosts = new HashMap<>();

        carTypeCosts.put(CarTypeConstants.COUPE, 15000d);
        carTypeCosts.put(CarTypeConstants.TRUCK, 25000d);
        carTypeCosts.put(CarTypeConstants.SUV, 30000d);
        carTypeCosts.put(CarTypeConstants.LUXURY_SEDAN, 35000d);

        return carTypeCosts.get(carType);
    }
}
