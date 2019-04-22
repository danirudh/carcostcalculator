package com.dynatrace.carcostcalculator;

import static com.dynatrace.carcostcalculator.CarTypeConstants.COUPE;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.dynatrace.carcostcalculator.repository.OptionCostRepository;

@RunWith(MockitoJUnitRunner.class)
public class OptionsCostCalculatorTest {

    @InjectMocks
    private OptionsCostCalculator sut;
    @Mock
    private OptionCostRepository optionCostRepository;

    @Test
    public void shouldReturnZeroAsCostIfdCarTypeIsNotAvailable() throws Exception {
        double expected = 0;
        final String carType = "randomcartype";

        when(optionCostRepository.getOptionsCost(carType)).thenReturn(emptyMap());

        assertThat(sut.calculate(carType, new String[] { "V8" }), is(expected));
    }

    @Test(expected = CarCostCalculationException.class)
    public void shouldThrowExceptionWhenTowPackageOptionIsSelectedForCoupe() throws Exception {

        when(optionCostRepository.getOptionsCost(COUPE)).thenReturn(singletonMap("BACK_CAMERA", 1000d));

        sut.calculate(COUPE, new String[] { "towpackage" });
    }

    @Test
    public void shouldReturnZeroAsCostIfOptionIsNotAvailable() throws Exception {
        double expected = 0;

        final String carType = COUPE;

        when(optionCostRepository.getOptionsCost(carType)).thenReturn(singletonMap("BACK_CAMERA", 1000d));

        assertThat(sut.calculate(carType, new String[] { "V8" }), is(expected));
    }

    @Test
    public void shouldReturnCostOfOptionForSelectedCarType() throws Exception {
        double expected = 5000;

        final String carType = COUPE;
        final String option = "BACK_CAMERA";

        when(optionCostRepository.getOptionsCost(carType)).thenReturn(singletonMap(option, 5000d));

        assertThat(sut.calculate(carType, new String[] { option }), is(expected));
    }
}