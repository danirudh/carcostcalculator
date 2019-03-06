package com.dynatrace.carcostcalculator.repository;

import static com.dynatrace.carcostcalculator.CarTypeConstants.SUV;
import static com.dynatrace.carcostcalculator.CarTypeConstants.TRUCK;

import org.springframework.stereotype.Repository;

@Repository
public class GasGuzzlerRepository {

    public String[] get() {
        return new String[] { TRUCK, SUV };
    }
}
