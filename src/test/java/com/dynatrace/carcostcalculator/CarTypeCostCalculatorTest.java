package com.dynatrace.carcostcalculator;

import static com.dynatrace.carcostcalculator.CarTypeConstants.COUPE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.dynatrace.carcostcalculator.repository.CarTypeCostRepository;

@RunWith(MockitoJUnitRunner.class)
public class CarTypeCostCalculatorTest {

    @InjectMocks
    private CarTypeCostCalculator sut;
    @Mock
    private CarTypeCostRepository carTypeCostRepository;

    @Test
    public void shouldReturnZeroWhenCostForRequestedCarTypeIsNotPresent() {
        double expected = 0;
        final String carType = "randomcartype";

        when(carTypeCostRepository.get(carType)).thenReturn(null);

        assertThat(sut.calculate(carType), is(expected));
    }

    @Test
    public void shouldReturnCarTypeCostForAGiveCarType() {
        Double expected = 15000d;
        final String carType = COUPE;

        when(carTypeCostRepository.get(carType)).thenReturn(expected);

        assertThat(sut.calculate(carType), is(expected));
    }
}