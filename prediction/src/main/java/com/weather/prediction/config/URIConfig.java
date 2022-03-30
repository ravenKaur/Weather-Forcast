package com.weather.prediction.config;

public enum URIConfig {
	ROOT("https://api.openweathermap.org/data/2.5/forecast"),
	CITY("q"),
	COUNT("cnt"),
	APP_KEY("appid"),
	KEY("KEY");
	

	private String value;

	URIConfig(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
