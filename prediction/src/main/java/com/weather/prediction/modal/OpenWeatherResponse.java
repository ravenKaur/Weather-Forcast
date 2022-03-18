package com.weather.prediction.modal;

import java.util.List;


public class OpenWeatherResponse {

	private String cod;
	private String message;
	private Integer cnt;
	private List<WeatherDataList> list;
	private City city;
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public List<WeatherDataList> getList() {
		return list;
	}
	public void setList(List<WeatherDataList> list) {
		this.list = list;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
}
