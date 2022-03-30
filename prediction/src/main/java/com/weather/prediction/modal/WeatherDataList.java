package com.weather.prediction.modal;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WeatherDataList {

	private String dt_txt;
	private LocalDate date;
	private Wind wind;
	private Cloud clouds;
	private List<Weather> weather;
	private WeatherMain main;
	public String getDt_txt() {
		return dt_txt;
	}
	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public Cloud getClouds() {
		return clouds;
	}
	public void setClouds(Cloud clouds) {
		this.clouds = clouds;
	}
	public List<Weather> getWeather() {
		return weather;
	}
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}
	public WeatherMain getMain() {
		return main;
	}
	public void setMain(WeatherMain main) {
		this.main = main;
	}
	
}
