package com.dynatrace.carcostcalculator.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dynatrace.carcostcalculator.CarCostCalculatorService;

@RunWith(MockitoJUnitRunner.class)
public class CarCostControllerTest {

    @InjectMocks
    private CarCostController sut;
    @Mock
    private CarCostCalculatorService carCostCalculatorService;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    @Test
    public void shouldReturnHttp412WhenCalculatorThrowsException() throws Exception {
        String carType = "type";
        String[] options = { "option" };
        String destinationZip = "12345";
        final double carCost = 1500d;

        when(carCostCalculatorService.calculate(carType, options, destinationZip)).thenReturn(carCost);

        //@formatter:off
        mockMvc.perform(get("/car/cost").param("carType", carType)
                                                   .param("options", options)
                                                   .param("destinationZip", destinationZip))
                .andExpect(status().isOk())
                .andExpect(jsonPath("carCost", CoreMatchers.is(carCost)));
        //@formatter:on
    }
}