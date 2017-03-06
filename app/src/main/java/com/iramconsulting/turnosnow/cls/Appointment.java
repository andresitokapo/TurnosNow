package com.iramconsulting.turnosnow.cls;

public class Appointment {
	
	String appointmentTime;
	String appointmentUri;
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getAppointmentUri() {
		return appointmentUri;
	}
	public void setAppointmentUri(String appointmentUri) {
		this.appointmentUri = appointmentUri;
	}
	
	@Override
	public String toString() {
		return "Appointment [appointmentTime=" + appointmentTime + ", appointmentUri=" + appointmentUri + "]";
	}
	
}
