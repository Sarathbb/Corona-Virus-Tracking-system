package com.coronaTracker.model;

import org.springframework.stereotype.Component;

@Component
public class LocationStates {

	private String States;
	private String Country;
	private int totalDeaths;
	private int changesLastDay;
	public String getStates() {
		return States;
	}
	public void setStates(String states) {
		States = states;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public int getTotalDeaths() {
		return totalDeaths;
	}
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	public int getChangesLastDay() {
		return changesLastDay;
	}
	public void setChangesLastDay(int changesLastDay) {
		this.changesLastDay = changesLastDay;
	}
	@Override
	public String toString() {
		return "LocationStates [States=" + States + ", Country=" + Country + ", totalDeaths=" + totalDeaths
				+ ", changesLastDay=" + changesLastDay + "]";
	}
	
	
}
