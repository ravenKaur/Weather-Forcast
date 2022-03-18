package com.weather.prediction.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.weather.prediction.modal.OpenWeatherResponse;
import com.weather.prediction.modal.WeatherForecastResponse;
import com.weather.prediction.service.IWeatherData;
import com.weather.prediction.service.IWeatherStation;
import com.weather.prediction.utility.URIConfig;

@Service
public class WeatherStationImpl implements IWeatherStation {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private IWeatherData weatherDataimpl;

	@Override
	public WeatherForecastResponse getWeatherForecast(final String cityName, final Integer count)
			throws URISyntaxException {
		WeatherForecastResponse weatherForecastResponse = null;
		URI uri = new URI(UriComponentsBuilder.fromHttpUrl(URIConfig.ROOT.getValue()).queryParam("q", cityName)
				.queryParam("appid", "d2929e9483efc82c82c32ee7e02d563e").queryParam("cnt", count).build()
				.toUriString());
		ResponseEntity<OpenWeatherResponse> weatherData = restTemplate.getForEntity(uri, OpenWeatherResponse.class);
		if (Objects.nonNull(weatherData) && Objects.nonNull(weatherData.getBody())) {
			final OpenWeatherResponse openWeatherResponse = weatherData.getBody();
			if (Objects.nonNull(openWeatherResponse.getList()) && !openWeatherResponse.getList().isEmpty()) {
				weatherForecastResponse = weatherDataimpl.setWeatherData(openWeatherResponse.getList());
				if (Objects.nonNull(weatherForecastResponse)) {
					weatherForecastResponse.setCity(cityName);
				}
			}
		}
		return weatherForecastResponse;
	}
}
