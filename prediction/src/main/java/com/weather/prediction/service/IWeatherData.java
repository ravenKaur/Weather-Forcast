package com.weather.prediction.service;

import java.util.List;

import com.weather.prediction.modal.WeatherDataList;
import com.weather.prediction.modal.WeatherForecastResponse;

public interface IWeatherData {

	public WeatherForecastResponse setWeatherData(List<WeatherDataList> list);
}
