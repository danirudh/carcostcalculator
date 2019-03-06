package com.dynatrace.carcostcalculator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class LuxuryTaxCalculatorTest {

    private LuxuryTaxCalculator sut;

    @Before
    public void setUp() {
        sut = new LuxuryTaxCalculator();
    }

    @Test
    public void shouldReturnLuxuryTaxOfZeroForCarCostLessThanThirtyThousand() {
        double expected = 0;

        assertThat(sut.calculate(15000), is(expected));
    }

    @Test
    public void shouldReturnLuxuryTaxForCarCostMoreThanThirtyThousand() {
        double expected = 850;

        assertThat(sut.calculate(45000), is(expected));
    }

    @Test
    public void shouldReturnLuxuryTaxForCarCostMoreThanSixtyThousand() {
        double expected = 1000;

        assertThat(sut.calculate(75000), is(expected));
    }

}