package com.dynatrace.carcostcalculator.repository;

import static com.dynatrace.carcostcalculator.CarTypeConstants.COUPE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class OptionCostRepositoryTest {

    private OptionCostRepository sut;

    @Before
    public void setUp() {
        sut = new OptionCostRepository();
    }

    @Test
    public void shouldReturnNullIfCarTypeIsNotPresent() {

        assertThat(sut.getOptionsCost("someCarType"), nullValue());
    }

    @Test
    public void shouldReturnOptionCostMap() {

        final Map<String, Double> actual = sut.getOptionsCost(COUPE);

        assertThat(actual.size(), is(5));
        assertThat(actual.get("V8"), is(5000d));
    }
}