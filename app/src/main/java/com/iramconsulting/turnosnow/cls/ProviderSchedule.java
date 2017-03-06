package com.iramconsulting.turnosnow.cls;

import java.util.HashMap;
import java.util.List;

public class ProviderSchedule {
	
	String name;
	HashMap<String, List<Appointment>> appointments = new HashMap<String, List<Appointment>>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<String, List<Appointment>> getAppointments() {
		return appointments;
	}
	public void setAppointments(HashMap<String, List<Appointment>> appointments) {
		this.appointments = appointments;
	}

	
	
}
