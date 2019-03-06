package com.dynatrace.carcostcalculator.functional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {
        "com.dynatrace.carcostcalculator" }, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.dynatrace.carcostcalculator.config.*"))
public class CucumberTestConfig {
}
