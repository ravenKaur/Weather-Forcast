package com.weather.prediction.service;

import java.net.URISyntaxException;

import com.weather.prediction.controller.ReturnEnitity;

public interface IWeatherStation {

	ReturnEnitity getWeatherForecast(String cityName, Integer count) throws URISyntaxException;
}
