package com.dynatrace.carcostcalculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dynatrace.carcostcalculator.CarCostCalculationException;

@ControllerAdvice
public class ControllerErrorHandlerAdvice {

    @ExceptionHandler(CarCostCalculationException.class)
    public ResponseEntity<Object> handleCarCostCalculationException(final CarCostCalculationException carCostCalculationException) {
        return new ResponseEntity<>(carCostCalculationException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
