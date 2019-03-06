package com.dynatrace.carcostcalculator.repository;

import static com.dynatrace.carcostcalculator.CarTypeConstants.COUPE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CarTypeCostCalculatorRepositoryTest {

    private CarTypeCostRepository sut;

    @Before
    public void setup() {
        sut = new CarTypeCostRepository();
    }

    @Test
    public void shouldReturnCarTypeCostAsNullIfDoesnotExist() {
        assertThat(sut.get("somerandom"), nullValue());
    }

    @Test
    public void shouldReturnCarTypeCost() {
        assertThat(sut.get(COUPE), is(15000d));
    }
}