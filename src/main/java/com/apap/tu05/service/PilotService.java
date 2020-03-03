package com.apap.tu05.service;

import com.apap.tu05.model.PilotModel;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	void addPilot(PilotModel pilot);
	void deletePilot(String licenseNumber);
	void updatePilot(String licenseNumber, PilotModel pilot);
}
