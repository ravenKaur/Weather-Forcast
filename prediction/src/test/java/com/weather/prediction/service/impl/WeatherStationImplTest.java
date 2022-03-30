package com.weather.prediction.service.impl;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.weather.prediction.modal.City;
import com.weather.prediction.modal.Cloud;
import com.weather.prediction.modal.OpenWeatherResponse;
import com.weather.prediction.modal.Weather;
import com.weather.prediction.modal.WeatherDataList;
import com.weather.prediction.modal.WeatherForecastResponse;
import com.weather.prediction.modal.WeatherMain;
import com.weather.prediction.modal.Wind;
import com.weather.prediction.service.IWeatherData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WeatherStationImplTest {

	@InjectMocks
	private WeatherStationImpl weatherStationImpl;

	@Mock
	private IWeatherData weatherDataimpl;

	@Mock
	private RestTemplate restTemplate;

	@Before
	public void init() {
		weatherDataimpl = mock(IWeatherData.class);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetWeatherForecast() throws Exception {
		URI uri = new URI(
				"https://api.openweathermap.org/data/2.5/forecast?q=london&appid=d2929e9483efc82c82c32ee7e02d563e");
		List<WeatherDataList> weatherList = new ArrayList<WeatherDataList>();
		WeatherDataList weatherData = new WeatherDataList();
		LocalDate date = LocalDate.now();
		weatherData.setDate(date);
		weatherData.setDt_txt("2022-03-01");
		Wind wind = new Wind();
		wind.setSpeed(5.5f);
		WeatherMain main = new WeatherMain();
		main.setTemp_max(202.2f);
		main.setTemp_min(130.3f);
		Cloud cloud = new Cloud();
		cloud.setAll("Cloudy");
		Weather weather = new Weather();
		weather.setId(1);
		weather.setDescription("It's great day.");
		weather.setIcon("icon");
		weather.setMain("main");
		List<Weather> wList = new ArrayList<>();
		wList.add(weather);
		weatherData.setWeather(wList);
		weatherData.setClouds(cloud);
		weatherData.setWind(wind);
		weatherData.setMain(main);
		weatherList.add(weatherData);
		OpenWeatherResponse openWeatherResponse = new OpenWeatherResponse();
		City city = new City();
		city.setName("london");
		openWeatherResponse.setCity(city);
		openWeatherResponse.setCnt(20);
		openWeatherResponse.setCod(HttpStatus.OK.toString());
		openWeatherResponse.setMessage("SUCCESS");
		openWeatherResponse.setList(weatherList);
		WeatherForecastResponse weatherForecastResponse = new WeatherForecastResponse();
		weatherForecastResponse.setMessage("It's great day.");
		weatherForecastResponse.setDate(date).setMaxTemperature(200.f).setMinTemperature(100.f).setWind(5.5f)
				.setMessage("Use sunscrenn");
		List<WeatherForecastResponse> list = new ArrayList<>();
		list.add(weatherForecastResponse);
		ResponseEntity<OpenWeatherResponse> mockResponse = Mockito
				.spy(new ResponseEntity<OpenWeatherResponse>(openWeatherResponse, HttpStatus.OK));
		doReturn(mockResponse).when(restTemplate).getForEntity(uri, OpenWeatherResponse.class);
		when(weatherDataimpl.setWeatherData(mockResponse.getBody().getList())).thenReturn(list);
		Assert.assertNotNull(weatherStationImpl.getWeatherForecast("london", 20));
	}

	//Likewise other scenario can be tested. Couldn't write more scenario due to time constraint.
}
