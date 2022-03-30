package com.weather.prediction.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.weather.prediction.config.URIConfig;
import com.weather.prediction.controller.Data;
import com.weather.prediction.controller.ReturnEnitity;
import com.weather.prediction.modal.OpenWeatherResponse;
import com.weather.prediction.modal.WeatherForecastResponse;
import com.weather.prediction.service.IWeatherData;
import com.weather.prediction.service.IWeatherStation;
import com.weather.prediction.utility.GeneralWeatherConstants;
import org.springframework.web.client.HttpClientErrorException.NotFound;;

/**
 * 
 * @author CORP\ravinderk This class behave as WeatherStation where the weather
 *         data is retrieved from public API.
 *
 */
@Service
public class WeatherStationImpl implements IWeatherStation {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private IWeatherData weatherDataimpl;

	/**
	 * This method calls the Public API to retrieve the weather status based on city
	 * name. For fault tolerance, Hystrix used to call the fallback method if
	 * underlying public API is not available, except exception ${NotFound.class}.
	 * Return the customized error information for exception ${NotFound.class}
	 */
	@HystrixCommand(ignoreExceptions = { NotFound.class }, fallbackMethod = "defaultWeatherStatus")
	@Override
	public ReturnEnitity getWeatherForecast(final String cityName, final Integer count) throws URISyntaxException {
		ReturnEnitity returnEntity = null;
		List<WeatherForecastResponse> weatherForecastResponseList = null;
		try {
			// Construct Public API URL
			URI uri = new URI(UriComponentsBuilder.fromHttpUrl(URIConfig.ROOT.getValue())
					.queryParam(URIConfig.CITY.getValue(), cityName)
					.queryParam(URIConfig.APP_KEY.getValue(), System.getenv(URIConfig.KEY.getValue()))
					.build().toUriString());
			// Synchronized call to Public API through RestTemplate.
			ResponseEntity<OpenWeatherResponse> weatherData = restTemplate.getForEntity(uri, OpenWeatherResponse.class);
			if (Objects.nonNull(weatherData) && Objects.nonNull(weatherData.getBody())) {
				 OpenWeatherResponse openWeatherResponse = weatherData.getBody();
				if (Objects.nonNull(openWeatherResponse.getList()) && !openWeatherResponse.getList().isEmpty()) {
					// Customize the returned weather data
					weatherForecastResponseList = weatherDataimpl.setWeatherData(openWeatherResponse.getList());
					if (Objects.nonNull(weatherForecastResponseList) && !weatherForecastResponseList.isEmpty()) {
						returnEntity = this.setResponse(cityName, weatherForecastResponseList, openWeatherResponse);
					}
				}
			}
		} catch (Exception ex) {
			throw ex;
		}
		return returnEntity;
	}

	/**
	 * Set the response returned by Public API
	 * 
	 * @param cityName
	 * @param weatherForecastResponse
	 * @param openWeatherResponse
	 * @return
	 */
	private ReturnEnitity setResponse(final String cityName,final  List<WeatherForecastResponse> weatherForecastResponseList,
			final OpenWeatherResponse openWeatherResponse) {
		ReturnEnitity returnEntity = new ReturnEnitity();
		returnEntity = new ReturnEnitity();
		Data<List<WeatherForecastResponse>> data = new Data<List<WeatherForecastResponse>>();
		data.setRecords(weatherForecastResponseList);
		returnEntity.setData(data).setStatus(openWeatherResponse.getCod()).setMessage(openWeatherResponse.getMessage());
		return returnEntity;
	}

	/**
	 * Circuit Breaker fallback method if the Public API is not responding
	 * 
	 * @param cityName
	 * @param count
	 * @return
	 */
	@SuppressWarnings("unused")
	private ReturnEnitity defaultWeatherStatus(final String cityName, final Integer count) {
		ReturnEnitity returnEnitity = new ReturnEnitity();
		returnEnitity.setMessage(GeneralWeatherConstants.DEFAULT_FALLBACK_RESPONSE);
		returnEnitity.setStatusCode(HttpStatus.NOT_FOUND.value());
		return returnEnitity;
	}

}
