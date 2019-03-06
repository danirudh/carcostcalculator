package com.dynatrace.carcostcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dynatrace.carcostcalculator.CarCostCalculationException;
import com.dynatrace.carcostcalculator.CarCostCalculatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api
public class CarCostController {

    @Autowired
    private CarCostCalculatorService carCostCalculatorService;

    @GetMapping("/car/cost")
    @ApiOperation(value = "Please send car type and options you want to add on", notes =
            "This service estimates the cost based on the car type and options you want to add on."
                    + "If Invalid carType (carType other than coupe, suv, truck, luxury_sedan) is sent, then service will simply return 0 as cost."
                    + "If invalid options are entered, it will not be considered.")
    //@formatter:off
    public ResponseEntity<CarCostResponse> getCarCost(@ApiParam(value = "Enter car type you want. Valid car types are: coupe, suv, truck, luxury_sedan")
                                             @RequestParam("carType") String carType,
                                             @ApiParam(value = "Enter the zip code of where you want to buy car")
                                             @RequestParam(value = "destinationZip", required = false) String destinationZip,
                                             @ApiParam(value = "Enter options you want to add on. Valid options are: v8, automatic, premiumaudio, sunroof, navigation, towpackage. Please note that tow                                                                  package is not available for coupe.")
                                             @RequestParam("options") String[] options) throws CarCostCalculationException {
    //@formatter:on

        CarCostResponse response = new CarCostResponse();
        response.setCarCost(carCostCalculatorService.calculate(carType, options, destinationZip));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
