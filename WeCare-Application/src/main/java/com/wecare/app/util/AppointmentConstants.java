package com.wecare.app.util;

public enum AppointmentConstants {

	APPPOINTMENT_NOT_FOUND("appointment.not.found"),
	USER_NOT_FOUND("user.not.found"),
	LIFECOACH_NOT_FOUND("lifecoach.not.found");
	
	private String message;

	private AppointmentConstants(String message) {
		this.message = message;
	}
	
    @Override
    public String toString() {
        return this.message;
    }
}
