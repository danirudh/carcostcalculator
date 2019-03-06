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
    private OptionCostCalculator optionCostCalculator;
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

        verifyZeroInteractions(carTypeCostCalculator, optionCostCalculator, luxuryTaxCalculator, gasGuzzlerTaxCalculator);
    }

    @Test
    public void shouldReturnZeroIfCarTypeIsEmpty() throws Exception {
        assertThat(sut.calculate("", new String[] { "someoption" }, "12345"), is(0d));

        verifyZeroInteractions(carTypeCostCalculator, optionCostCalculator, luxuryTaxCalculator, gasGuzzlerTaxCalculator);
    }

    @Test
    public void shouldReturnZeroIfOptionsIsNull() throws Exception {
        assertThat(sut.calculate(carType, null, "12345"), is(0d));

        verifyZeroInteractions(carTypeCostCalculator, optionCostCalculator, luxuryTaxCalculator, gasGuzzlerTaxCalculator);
    }

    @Test
    public void shouldReturnZeroIfOptionsLengthIsZero() throws Exception {
        assertThat(sut.calculate(carType, new String[] {}, "12345"), is(0d));

        verifyZeroInteractions(carTypeCostCalculator, optionCostCalculator, luxuryTaxCalculator, gasGuzzlerTaxCalculator);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInvalidOptionTypeIsSelected() throws Exception {

        String option = "option";

        when(optionCostCalculator.calculate(carType, option)).thenThrow(new Exception("some exception"));

        sut.calculate(carType, new String[] { option }, "12345");
    }

    @Test
    public void shouldReturnTotalCostOfCar() throws Exception {
        double option1Cost = 200;
        String option1 = "option1";
        double option2Cost = 300;
        String option2 = "option2";
        String destinationZip = "12345";
        double gasGuzzlerTax = 50;
        double luxuryTax = 75;
        double expected = carTypeCost + option1Cost + option2Cost + gasGuzzlerTax + luxuryTax;

        when(optionCostCalculator.calculate(carType, option1)).thenReturn(option1Cost);
        when(optionCostCalculator.calculate(carType, option2)).thenReturn(option2Cost);
        when(gasGuzzlerTaxCalculator.calculate(carType, destinationZip)).thenReturn(gasGuzzlerTax);
        when(luxuryTaxCalculator.calculate(any(double.class))).thenReturn(luxuryTax);

        assertThat(sut.calculate(carType, new String[] { option1, option2 }, destinationZip), is(expected));
    }

}