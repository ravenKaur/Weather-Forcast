package com.weather.prediction.utility;

public enum URIConfig {
	ROOT("https://api.openweathermap.org/data/2.5/forecast");

	private String value;

	URIConfig(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
