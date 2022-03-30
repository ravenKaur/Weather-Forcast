package com.weather.prediction.modal;



import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class WeatherForecastResponse {

	private float maxTemperature;
	private float minTemperature;
	private float wind;
	private String message;
	private LocalDate date;
	private List<WeatherDataList> weatherDataList;
	
	public float getMaxTemperature() {
		return maxTemperature;
	}

	public WeatherForecastResponse setMaxTemperature(float maxTemperature) {
		this.maxTemperature = maxTemperature;
		return this;
	}

	public float getMinTemperature() {
		return minTemperature;
	}

	public WeatherForecastResponse setMinTemperature(float minTemperature) {
		this.minTemperature = minTemperature;
		return this;
	}

	public float getWind() {
		return wind;
	}

	public WeatherForecastResponse setWind(float wind) {
		this.wind = wind;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public WeatherForecastResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public LocalDate getDate() {
		return date;
	}

	public WeatherForecastResponse setDate(LocalDate date) {
		this.date = date;
		return this;
	}

	public List<WeatherDataList> getWeatherDataList() {
		return weatherDataList;
	}

	public WeatherForecastResponse setWeatherDataList(List<WeatherDataList> weatherDataList) {
		this.weatherDataList = weatherDataList;
		return this;
	}
	
}
