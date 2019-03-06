package com.dynatrace.carcostcalculator.repository;

import static com.dynatrace.carcostcalculator.CarTypeConstants.COUPE;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dynatrace.carcostcalculator.CarTypeConstants;

@Repository
public class OptionCostRepository {

    private static final String ENGINE_TYPE = "V8";
    private static final String TRANSMISSION_TYPE = "AUTOMATIC";
    private static final String MUSIC_TYPE = "PREMIUMAUDIO";
    private static final String SUNROOF = "SUNROOF";
    private static final String NAVIGATION = "NAVIGATION";
    private static final String TOW_PACKAGE = "TOWPACKAGE";

    public Map<String, Double> getOptionsCost(String carType) {
        Map<String, Map<String, Double>> optionCosts = new HashMap<>();

        Map<String, Double> coupeOptionsCost = new HashMap<>();
        coupeOptionsCost.put(ENGINE_TYPE, 5000d);
        coupeOptionsCost.put(TRANSMISSION_TYPE, 1000d);
        coupeOptionsCost.put(MUSIC_TYPE, 1000d);
        coupeOptionsCost.put(SUNROOF, 1000d);
        coupeOptionsCost.put(NAVIGATION, 1000d);
        optionCosts.put(COUPE, coupeOptionsCost);

        Map<String, Double> truckOptionsCost = new HashMap<>();
        truckOptionsCost.put(ENGINE_TYPE, 6000d);
        truckOptionsCost.put(TRANSMISSION_TYPE, 1500d);
        truckOptionsCost.put(MUSIC_TYPE, 1100d);
        truckOptionsCost.put(SUNROOF, 1000d);
        truckOptionsCost.put(NAVIGATION, 1000d);
        truckOptionsCost.put(TOW_PACKAGE, 550d);
        optionCosts.put(CarTypeConstants.TRUCK, truckOptionsCost);

        Map<String, Double> suvOptionsCost = new HashMap<>();
        suvOptionsCost.put(ENGINE_TYPE, 5500d);
        suvOptionsCost.put(TRANSMISSION_TYPE, 1200d);
        suvOptionsCost.put(MUSIC_TYPE, 1500d);
        suvOptionsCost.put(SUNROOF, 1000d);
        suvOptionsCost.put(NAVIGATION, 1000d);
        suvOptionsCost.put(TOW_PACKAGE, 500d);
        optionCosts.put(CarTypeConstants.SUV, suvOptionsCost);

        Map<String, Double> luxuryOptionsCost = new HashMap<>();
        // Cost unusually high
        luxuryOptionsCost.put(ENGINE_TYPE, 25000d);
        luxuryOptionsCost.put(TRANSMISSION_TYPE, 1200d);
        luxuryOptionsCost.put(MUSIC_TYPE, 1500d);
        luxuryOptionsCost.put(SUNROOF, 1000d);
        luxuryOptionsCost.put(NAVIGATION, 1000d);
        luxuryOptionsCost.put(TOW_PACKAGE, 500d);
        optionCosts.put(CarTypeConstants.LUXURY_SEDAN, luxuryOptionsCost);

        return optionCosts.get(carType);
    }
}
