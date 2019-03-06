package com.dynatrace.carcostcalculator.repository;

import static com.dynatrace.carcostcalculator.CarTypeConstants.SUV;
import static com.dynatrace.carcostcalculator.CarTypeConstants.TRUCK;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class GasGuzzlerRepositoryTest {

    private GasGuzzlerRepository sut;

    @Before
    public void setUp() {
        sut = new GasGuzzlerRepository();
    }

    @Test
    public void shouldReturnGasGuzzlers() {
        String[] actual = sut.get();

        assertThat(actual.length, is(2));
        assertThat(actual[0], is(TRUCK));
        assertThat(actual[1], is(SUV));
    }
}