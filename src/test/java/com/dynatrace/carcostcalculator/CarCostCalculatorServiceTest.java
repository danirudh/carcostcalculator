package com.dynatrace.carcostcalculator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarCostCalculatorServiceTest {

    @InjectMocks
    private CarCostCalculatorService sut;
    @Mock
    private CarTypeCostCalculator carTypeCostCalculator;
    @Mock
    private OptionsCostCalculator optionsCostCalculator;
    @Mock
    private LuxuryTaxCalculator luxuryTaxCalculator;
    @Mock
    private GasGuzzlerTaxCalculator gasGuzzlerTaxCalculator;
    private double carTypeCost;
    private String carType;

    @Before
    public void setUp() {
        carTypeCost = 100;
        carType = "carType";

        when(carTypeCostCalculator.calculate(carType)).thenReturn(carTypeCost);
    }

    @Test
    public void shouldReturnZeroIfCarTypeIsNull() throws Exception {
        assertThat(sut.calculate(null, new String[] { "someoption" }, "12345"), is(0d));

        verifyZeroInteractions(carTypeCostCalculator, optionsCostCalculator, luxuryTaxCalculator, gasGuzzlerTaxCalculator);
    }

    @Test
    public void shouldReturnZeroIfCarTypeIsEmpty() throws Exception {
        assertThat(sut.calculate("", new String[] { "someoption" }, "12345"), is(0d));

        verifyZeroInteractions(carTypeCostCalculator, optionsCostCalculator, luxuryTaxCalculator, gasGuzzlerTaxCalculator);
    }

    @Test
    public void shouldReturnZeroIfOptionsIsNull() throws Exception {
        assertThat(sut.calculate(carType, null, "12345"), is(0d));

        verifyZeroInteractions(carTypeCostCalculator, optionsCostCalculator, luxuryTaxCalculator, gasGuzzlerTaxCalculator);
    }

    @Test
    public void shouldReturnZeroIfOptionsLengthIsZero() throws Exception {
        assertThat(sut.calculate(carType, new String[] {}, "12345"), is(0d));

        verifyZeroInteractions(carTypeCostCalculator, optionsCostCalculator, luxuryTaxCalculator, gasGuzzlerTaxCalculator);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInvalidOptionTypeIsSelected() throws Exception {

        final String[] options = { "option" };

        when(optionsCostCalculator.calculate(carType, options)).thenThrow(new Exception("some exception"));

        sut.calculate(carType, options, "12345");
    }

    @Test
    public void shouldReturnTotalCostOfCar() throws Exception {
        double optionsCost = 500;
        String destinationZip = "12345";
        double gasGuzzlerTax = 50;
        double luxuryTax = 75;
        double expected = carTypeCost + optionsCost + gasGuzzlerTax + luxuryTax;
        final String[] options = { "option1", "option2" };

        when(optionsCostCalculator.calculate(carType, options)).thenReturn(optionsCost);
        when(gasGuzzlerTaxCalculator.calculate(carType, destinationZip)).thenReturn(gasGuzzlerTax);
        when(luxuryTaxCalculator.calculate(any(double.class))).thenReturn(luxuryTax);

        assertThat(sut.calculate(carType, options, destinationZip), is(expected));
    }

}