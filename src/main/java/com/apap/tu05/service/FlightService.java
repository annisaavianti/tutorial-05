package com.apap.tu05.service;

import java.util.List;

import com.apap.tu05.model.FlightModel;

/**
* FlightService
*/

public interface FlightService {
	void addFlight(FlightModel flight);
	void deleteFlight(String flightNumber);
	void deleteFlightById(long id);
	void updateFlight(String flightNumber, FlightModel flight);
	List<FlightModel> getFlightList();
	FlightModel getFlightById(long id);
	FlightModel getFlightDetail(String flightNumber);
}