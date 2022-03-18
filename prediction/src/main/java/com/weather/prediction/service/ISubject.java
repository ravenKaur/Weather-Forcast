package com.weather.prediction.service;


public interface ISubject {

	public void registerObserver(IWeatherObserver observer);

	public void removeObserver(IWeatherObserver observer);

	public void notifyObservers();

}
