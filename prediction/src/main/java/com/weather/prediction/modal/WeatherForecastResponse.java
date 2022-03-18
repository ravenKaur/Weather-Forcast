package com.weather.prediction.modal;

public class WeatherForecastResponse {

	private WeatherForecast today;

	private WeatherForecast tomorrow;

	private WeatherForecast dateAfterTomorrow;

	private String city;

	public String getCity() {
		return city;
	}

	public WeatherForecastResponse setCity(String city) {
		this.city = city;
		return this;
	}

	public WeatherForecast getToday() {
		return today;
	}

	public WeatherForecastResponse setToday(WeatherForecast today) {
		this.today = today;
		return this;
	}

	public WeatherForecast getTomorrow() {
		return tomorrow;
	}

	public WeatherForecastResponse setTomorrow(WeatherForecast tomorrow) {
		this.tomorrow = tomorrow;
		return this;
	}

	public WeatherForecast getDateAfterTomorrow() {
		return dateAfterTomorrow;
	}

	public WeatherForecastResponse setDateAfterTomorrow(WeatherForecast dateAfterTomorrow) {
		this.dateAfterTomorrow = dateAfterTomorrow;
		return this;
	}

}
