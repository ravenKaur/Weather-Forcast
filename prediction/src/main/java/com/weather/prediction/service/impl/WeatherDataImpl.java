package com.weather.prediction.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.weather.prediction.modal.WeatherDataList;
import com.weather.prediction.modal.WeatherForecastResponse;
import com.weather.prediction.modal.WeatherMain;
import com.weather.prediction.modal.Wind;
import com.weather.prediction.service.IWeatherData;
import com.weather.prediction.utility.GeneralWeatherConstants;
import com.weather.prediction.utility.Utility;

/**
 * 
 * @author CORP\ravinderk Customize the retrieve data from public API as per
 *         business logic.
 */
@Service
public class WeatherDataImpl implements IWeatherData {


	/**
	 * This method set the customized weather message based on the retrieved public
	 * API data. The response is customized for next 2 days including today.
	 */
	@Override
	public List<WeatherForecastResponse> setWeatherData(List<WeatherDataList> weatherDataList) {
		LocalDate todaysDate = LocalDate.now();
		List<WeatherForecastResponse> weatherForecastResponseList = null;
		if (Objects.nonNull(weatherDataList) && !weatherDataList.isEmpty()) {
			try {
				weatherForecastResponseList = new ArrayList<>();
				for (int i = GeneralWeatherConstants.TODAY; i <= GeneralWeatherConstants.MAX_DAYS; i++) {
					final WeatherForecastResponse weatherData = this.weatherAnalysis(weatherDataList,
							todaysDate.plusDays(i));
					weatherForecastResponseList.add(weatherData);
				}
			} catch (Exception ex) {
				throw ex;
			}
		}
		return weatherForecastResponseList;
	}


	/**
	 * Set Max,min temperature and Wind status along with customized weather status
	 * 
	 * @param weatherDataList
	 * @param localDate
	 * @return
	 */
	private WeatherForecastResponse weatherAnalysis(final List<WeatherDataList> weatherDataList, final LocalDate localDate) {
		// Get Temperature
		List<WeatherMain> todaysDataList = this.getDateWiseWeatherData(weatherDataList, localDate);
		float max = this.getMaxTemperature(todaysDataList);
		float min = this.getMinTemperature(todaysDataList);
		// Get Wind
		float maxWindSpeed = this.getWindSpeed(weatherDataList, localDate);
		return this.updateWeatherStatus(max, min, maxWindSpeed, localDate, weatherDataList);
	}

	/**
	 * Customization of weather status
	 * updateWeatherStatus
	 * @param max
	 * @param min
	 * @param maxWindSpeed
	 * @param localDate
	 * @param weatherDataList
	 * @return
	 */
	private WeatherForecastResponse updateWeatherStatus(float max, float min, float maxWindSpeed, LocalDate localDate,
			List<WeatherDataList> weatherDataList) {
		final WeatherForecastResponse weatherForecast = new WeatherForecastResponse();
		weatherForecast.setMaxTemperature(max).setMinTemperature(min).setWind(maxWindSpeed).setDate(localDate);
				//.setWeatherDataList(weatherDataList);
		if (maxWindSpeed > GeneralWeatherConstants.WIND_SPEED) {
			weatherForecast.setMessage(GeneralWeatherConstants.WIND_MESSAGE);
		} else if (max > GeneralWeatherConstants.TEMPERATURE) {
			weatherForecast.setMessage(GeneralWeatherConstants.TEMP_MESSAGE);
		} else {
			weatherForecast.setMessage(GeneralWeatherConstants.GREAT_DAY);
		}
		return weatherForecast;
	}

	/**
	 * Get max wind during the day
	 * 
	 * @param weatherDataList
	 * @param todaysDate
	 * @return
	 */
	private float getWindSpeed(final List<WeatherDataList> weatherDataList, final LocalDate todaysDate) {
		return weatherDataList.stream().filter(p -> todaysDate.equals(Utility.string2Date(p.getDt_txt())))
				.map(WeatherDataList::getWind).max(Comparator.comparing(Wind::getSpeed)).get().getSpeed();
	}

	/**
	 * Get min temperature during the day
	 * 
	 * @param todaysDataList
	 * @return
	 */
	private float getMinTemperature(final List<WeatherMain> todaysDataList) {
		return todaysDataList.stream().min(Comparator.comparing(WeatherMain::getTemp_min)).get().getTemp_min();
	}

	/**
	 * Get Max temperature during the day
	 * 
	 * @param todaysDataList
	 * @return
	 */
	private float getMaxTemperature(final List<WeatherMain> weatherDataList) {
		if (Objects.nonNull(weatherDataList) && !weatherDataList.isEmpty()) {
			return weatherDataList.stream().max(Comparator.comparing(WeatherMain::getTemp_max)).get().getTemp_max();
		}
		return 0;
	}

	/**
	 * Get data for a specific date
	 * 
	 * @param weatherDataList
	 * @param date
	 * @return
	 */
	private List<WeatherMain> getDateWiseWeatherData(final List<WeatherDataList> weatherDataList,
			final LocalDate date) {
		return weatherDataList.stream().filter(p -> date.equals(Utility.string2Date(p.getDt_txt())))
				.map(WeatherDataList::getMain).collect(Collectors.toList());
	}

}
