package com.weather.prediction.controller;

import lombok.Getter;
import lombok.Setter;

public class Data<T> {
	/** The records. */
	private T records;

	public T getRecords() {
		return records;
	}

	public void setRecords(T records) {
		this.records = records;
	}
	

}
