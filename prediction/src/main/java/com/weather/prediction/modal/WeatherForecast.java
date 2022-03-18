package com.weather.prediction.modal;

public class WeatherForecast {

	private float maxTemperature;
	private float minTemperature;
	private float wind;
	private String message;
	public float getMaxTemperature() {
		return maxTemperature;
	}
	public WeatherForecast setMaxTemperature(float maxTemperature) {
		this.maxTemperature = maxTemperature;
		return this;
	}
	public float getMinTemperature() {
		return minTemperature;
	}
	public WeatherForecast setMinTemperature(float minTemperature) {
		this.minTemperature = minTemperature;
		return this;
	}
	public float getWind() {
		return wind;
	}
	public WeatherForecast setWind(float wind) {
		this.wind = wind;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public WeatherForecast setMessage(String message) {
		this.message = message;
		return this;
	}
}
