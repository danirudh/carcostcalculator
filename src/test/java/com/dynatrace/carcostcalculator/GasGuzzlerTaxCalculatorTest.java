package com.dynatrace.carcostcalculator;

import static com.dynatrace.carcostcalculator.CarTypeConstants.LUXURY_SEDAN;
import static com.dynatrace.carcostcalculator.CarTypeConstants.SUV;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.dynatrace.carcostcalculator.repository.GasGuzzlerRepository;

@RunWith(MockitoJUnitRunner.class)
public class GasGuzzlerTaxCalculatorTest {

    @InjectMocks
    private GasGuzzlerTaxCalculator sut;
    @Mock
    private GasGuzzlerRepository gasGuzzlerRepository;

    @Test
    public void shouldReturnZeroIfCarIsNotAGasGuzzler() {
        double expected = 0;

        when(gasGuzzlerRepository.get()).thenReturn(new String[] { SUV });

        assertThat(sut.calculate(LUXURY_SEDAN, "somezip"), is(expected));
    }

    @Test
    public void shouldReturnTaxIfCarIsAGasGuzzler() {
        double expected = 1500;

        when(gasGuzzlerRepository.get()).thenReturn(new String[] { SUV });

        assertThat(sut.calculate(SUV, "somezip"), is(expected));
    }
}