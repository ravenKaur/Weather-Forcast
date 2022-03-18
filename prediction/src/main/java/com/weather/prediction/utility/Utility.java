package com.weather.prediction.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.logging.log4j.util.Strings;

public interface Utility {

	public static LocalDate string2Date(String date) {
		if (Strings.isNotBlank(date)) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			LocalDate d = null;
			try {
				Date d1 = format.parse(date);
				d= d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			} catch (Exception e) {
			}
			return d;
		}
		return null;
	}
}
