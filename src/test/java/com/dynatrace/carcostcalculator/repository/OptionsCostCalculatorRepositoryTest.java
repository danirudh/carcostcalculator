package com.dynatrace.carcostcalculator.repository;

import static com.dynatrace.carcostcalculator.CarTypeConstants.COUPE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class OptionsCostCalculatorRepositoryTest {

    private OptionCostRepository sut;

    @Before
    public void setUp() {
        sut = new OptionCostRepository();
    }

    @Test
    public void shouldReturnOptionCostsForACarType() {

        Map<String, Double> actual = sut.getOptionsCost(COUPE);

        assertThat(actual.size(), is(5));
        assertThat(actual.get("V8"), is(5000d));
    }
}