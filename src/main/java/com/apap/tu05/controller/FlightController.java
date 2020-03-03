package com.apap.tu05.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tu05.model.FlightModel;
import com.apap.tu05.model.PilotModel;
import com.apap.tu05.service.FlightService;
import com.apap.tu05.service.PilotService;

/**
 * FlightController
 */

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		ArrayList<FlightModel> pilotFlight = new ArrayList<>();
		pilotFlight.add(flight);
		pilot.setPilotFlight(pilotFlight);
		flight.setPilot(pilot);
		model.addAttribute("pilot", pilot);
		model.addAttribute("flight", flight);
		model.addAttribute("nav", "ADD FLIGHT");
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST, params = {"addRow"})
	private String addFlightRow(@ModelAttribute PilotModel pilot, BindingResult bindingResult, Model model) {
		FlightModel newFlight = new FlightModel();
		pilot.getPilotFlight().add(newFlight);
		model.addAttribute("pilot", pilot);
		model.addAttribute("nav", "ADD FLIGHT");
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST, params = {"submit"})
	private String addFlightSubmit(@ModelAttribute PilotModel pilot, Model model) {
		PilotModel currPilot = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
		for (FlightModel flight : pilot.getPilotFlight()) {
			flight.setPilot(currPilot);
			flightService.addFlight(flight);
		}
		model.addAttribute("nav", "ADD FLIGHT");
		return "add";
	}
	
	@RequestMapping(value = "/flight/delete/{flightNumber}", method = RequestMethod.GET)
	private String deleteFlight(@PathVariable(value = "flightNumber") String flightNumber, Model model) {
		flightService.deleteFlight(flightNumber);
		return "delete";
	}
	
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for (FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlightById(flight.getId());
		}
		model.addAttribute("nav", "DELETE FLIGHT");
		return "delete";
	}
	
	@RequestMapping(value = "/flight/update/{flightNumber}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable(value = "flightNumber") String flightNumber, Model model) {
		FlightModel flight = flightService.getFlightDetail(flightNumber);
		model.addAttribute("flight", flight);
		model.addAttribute("updFlight", new FlightModel());
		model.addAttribute("nav", "UPDATE FLIGHT");
		return "updateFlight";
	}
	
	@RequestMapping(value = "/flight/update/{flightNumber}", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel updFlight, @PathVariable(value = "flightNumber") String flightNumber, Model model) {
		flightService.updateFlight(flightNumber, updFlight);
		model.addAttribute("nav", "UPDATE FLIGHT");
		return "update";
	}
	
	@RequestMapping(value = "/flight/view", method = RequestMethod.GET)
	private String viewFlight(Model model) {
		List<FlightModel> flight = flightService.getFlightList();
		model.addAttribute("flight", flight);
		model.addAttribute("nav", "FLIGHT DATA");
		return "view-flight";
	}
}
