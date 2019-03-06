package com.dynatrace.carcostcalculator.functional;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features" }, plugin = { "html:target/cucumber-html-report" })
@ContextConfiguration(classes = CucumberTestConfig.class)
public class CarCostCalculatorCukesTest {
}
