package com.weather.prediction.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.prediction.modal.OpenWeatherResponse;
import com.weather.prediction.modal.WeatherForecastResponse;
import com.weather.prediction.service.IWeatherStation;
import com.weather.prediction.utility.General;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/data")
public class WeatherPredictionController {
	
	@Autowired
	private IWeatherStation weatherStation;

	@ApiOperation(value = "This service designed to get employee's eligibility to perform LE.")
	@ApiResponses(value = {
			@ApiResponse(code = General.HTTP_OK, message = General.SUCCESS, response = ReturnConvertor.class),
			@ApiResponse(code = General.HTTP_NO_CONTENT, message = General.SUCCESS, response = ReturnConvertor.ErrorDoc.class),
			@ApiResponse(code = General.HTTP_NOT_FOUND, message = General.SUCCESS, response = ReturnConvertor.ErrorDoc.class),
			@ApiResponse(code = General.HTTP_METHOD_NOT_ALLOWED, message = General.SUCCESS, response = ReturnConvertor.ErrorDoc.class) })
	@RequestMapping("/forecast")
	public ReturnConvertor checkEligibilityForLE(@RequestParam(required = false) String cityName,
			@RequestParam(required = false) Integer count) throws URISyntaxException {
		ReturnConvertor returnConvertor = new ReturnConvertor();
		WeatherForecastResponse  weatherData =weatherStation.getWeatherForecast(cityName, count);
		Data<WeatherForecastResponse> data = new Data<WeatherForecastResponse>();
		data.setRecords(weatherData);
		returnConvertor.setData(data);
		returnConvertor.success();
		return returnConvertor;
	}
}
