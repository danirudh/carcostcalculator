package com.dynatrace.carcostcalculator.functional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.dynatrace.carcostcalculator.controller.CarCostController;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = CucumberTestConfig.class)
public class CarCostCalculatorStepDefs {

    private double carCost;
    @Autowired
    private CarCostController carCostController;

    @Given("^that user wants to know the cost of the car$")
    public void that_user_wants_to_know_the_cost_of_the_car() {
    }

    @When("^a request is made to for (.*) with (.*) and (.*)$")
    public void a_request_is_made_to_for_coupe_with_premiumaudio_and(String carType, String optionsString, String destinationZip) throws Exception {
        String[] options = optionsString.split(",");
        carCost = carCostController.getCarCost(carType, destinationZip, options).getBody().getCarCost();
    }

    @Then("^the (.*) of the car is returned$")
    public void the_price_of_the_car_is_returned(double expectedCarCost) {
        assertThat(carCost, is(expectedCarCost));
    }

}
