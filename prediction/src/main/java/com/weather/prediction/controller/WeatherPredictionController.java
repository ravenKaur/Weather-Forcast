package com.weather.prediction.controller;

import java.net.URISyntaxException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.prediction.config.EnableSwaggerDoc;
import com.weather.prediction.service.IWeatherStation;
import com.weather.prediction.utility.General;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@EnableSwaggerDoc
public class WeatherPredictionController {

	@Autowired
	private IWeatherStation weatherStation;

	@ApiOperation(value = "This service designed to get Weather Report.")
	@ApiResponses(value = {
			@ApiResponse(code = General.HTTP_OK, message = General.SUCCESS, response = ReturnEnitity.class),
			@ApiResponse(code = General.HTTP_NO_CONTENT, message = General.SUCCESS, response = ReturnEnitity.ErrorDoc.class),
			@ApiResponse(code = General.HTTP_NOT_FOUND, message = General.SUCCESS, response = ReturnEnitity.ErrorDoc.class),
			@ApiResponse(code = General.HTTP_METHOD_NOT_ALLOWED, message = General.SUCCESS, response = ReturnEnitity.ErrorDoc.class) })
	@RequestMapping("/forecast")
	public ReturnEnitity forecast(@RequestParam(required = false) String cityName,
			@RequestParam(required = false) Integer count) throws URISyntaxException {
		ReturnEnitity returnConvertor = null;
		try {
			returnConvertor = weatherStation.getWeatherForecast(cityName, count);
			Link selfLink = WebMvcLinkBuilder.linkTo(WeatherPredictionController.class).slash("/swagger-ui").slash("/")
					.withSelfRel();
			if (Objects.nonNull(returnConvertor)) {
				returnConvertor.add(selfLink);
			}
		} catch (Exception ex) {
			returnConvertor = new ReturnEnitity();
			returnConvertor.setMessage(ex.getMessage());
		}
		return returnConvertor;
	}
}
