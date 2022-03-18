package com.weather.prediction.service;

import java.net.URISyntaxException;

import com.weather.prediction.modal.OpenWeatherResponse;
import com.weather.prediction.modal.WeatherForecastResponse;

public interface IWeatherStation {

	WeatherForecastResponse getWeatherForecast(String cityName, Integer count) throws URISyntaxException;
}
