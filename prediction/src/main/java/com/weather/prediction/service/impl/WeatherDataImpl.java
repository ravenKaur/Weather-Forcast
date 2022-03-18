package com.weather.prediction.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.weather.prediction.modal.WeatherDataList;
import com.weather.prediction.modal.WeatherForecast;
import com.weather.prediction.modal.WeatherForecastResponse;
import com.weather.prediction.modal.WeatherMain;
import com.weather.prediction.modal.Wind;
import com.weather.prediction.service.IWeatherData;
import com.weather.prediction.utility.GeneralWeatherConstants;
import com.weather.prediction.utility.Utility;

@Service
public class WeatherDataImpl implements IWeatherData {

	@Override
	public WeatherForecastResponse setWeatherData(List<WeatherDataList> weatherDataList) {
		WeatherForecastResponse weatherForecastResponse = new WeatherForecastResponse();
		LocalDate todaysDate = LocalDate.now();
		if (Objects.nonNull(weatherDataList) && !weatherDataList.isEmpty()) {
			try {
				WeatherForecast today = this.weatherAnalysis(weatherDataList, todaysDate);
				weatherForecastResponse.setToday(today);
				WeatherForecast tomorrow = this.weatherAnalysis(weatherDataList, todaysDate.plusDays(1));
				weatherForecastResponse.setTomorrow(tomorrow);
				WeatherForecast dayAfterTomorrow = this.weatherAnalysis(weatherDataList, todaysDate.plusDays(2));
				weatherForecastResponse.setDateAfterTomorrow(dayAfterTomorrow);
			} catch (Exception ex) {
			}
		}

		return weatherForecastResponse;
	}

	private WeatherForecast weatherAnalysis(List<WeatherDataList> weatherDataList, LocalDate localDate) {
		// Get Temperature
		List<WeatherMain> todaysDataList = this.getDateWiseWeatherData(weatherDataList, localDate);
		float max = this.getMaxTemperature(todaysDataList);
		float min = this.getMinTemperature(todaysDataList);
		// Get Wind
		float maxWindSpeed = this.getWindSpeed(weatherDataList, localDate);
		return this.updateWeatherStatus(max, min, maxWindSpeed);
	}

	private WeatherForecast updateWeatherStatus(float max, float min, float maxWindSpeed) {
		final WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.setMaxTemperature(max).setMinTemperature(min).setWind(maxWindSpeed);
		if (maxWindSpeed > GeneralWeatherConstants.WIND_SPEED) {
			weatherForecast.setMessage(GeneralWeatherConstants.WIND_MESSAGE);
		} else if (max > GeneralWeatherConstants.TEMPERATURE) {
			weatherForecast.setMessage(GeneralWeatherConstants.TEMP_MESSAGE);
		} else {
			weatherForecast.setMessage(GeneralWeatherConstants.GREAT_DAY);
		}
		return weatherForecast;
	}

	private float getWindSpeed(List<WeatherDataList> weatherDataList, LocalDate todaysDate) {
		return weatherDataList.stream().filter(p -> todaysDate.equals(Utility.string2Date(p.getDt_txt())))
				.map(WeatherDataList::getWind).max(Comparator.comparing(Wind::getSpeed)).get().getSpeed();
	}

	private float getMinTemperature(List<WeatherMain> todaysDataList) {
		return todaysDataList.stream().min(Comparator.comparing(WeatherMain::getTemp_min)).get().getTemp_min();
	}

	private float getMaxTemperature(List<WeatherMain> todaysDataList) {
		if (Objects.nonNull(todaysDataList) && !todaysDataList.isEmpty()) {
			return todaysDataList.stream().max(Comparator.comparing(WeatherMain::getTemp_max)).get().getTemp_max();
		}
		return 0;
	}

	private List<WeatherMain> getDateWiseWeatherData(List<WeatherDataList> weatherDataList, LocalDate date) {
		return weatherDataList.stream().filter(p -> date.equals(Utility.string2Date(p.getDt_txt())))
				.map(WeatherDataList::getMain).collect(Collectors.toList());
	}
}
