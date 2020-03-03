package com.apap.tu05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tu05.model.FlightModel;
import com.apap.tu05.repository.FlightDb;

/**
 * FlightServiceImpl
 */

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDb flightDb;

	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}

	@Override
	public List<FlightModel> getFlightList() {
		return flightDb.findAll();
	}

	@Override
	public FlightModel getFlightDetail(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
	}
	
	@Override
	public void deleteFlight(String flightNumber) {
		flightDb.delete(this.getFlightDetail(flightNumber));
	}
	
	@Override
	public void deleteFlightById(long id) {
		flightDb.delete(this.getFlightById(id));
	}

	@Override
	public FlightModel getFlightById(long id) {
		return flightDb.findById(id);
	}

	@Override
	public void updateFlight(String flightNumber, FlightModel updFlight) {
		FlightModel flight = this.getFlightDetail(flightNumber);
		flight.setFlightNumber(updFlight.getFlightNumber());
		flight.setOrigin(updFlight.getOrigin());
		flight.setDestination(updFlight.getDestination());
		flight.setTime(updFlight.getTime());
	}
}
