package com.wecare.app.service;

import java.time.LocalDateTime;
import java.util.List;

import com.wecare.app.dto.AppointmentDTO;
import com.wecare.app.exception.AppointmentNotFoundException;

public interface ApplicationService {

	AppointmentDTO rescheduleAppointment(int appointmentId, LocalDateTime date) throws AppointmentNotFoundException;
	
	AppointmentDTO cancelAppointment(int appointmentId) throws AppointmentNotFoundException, AppointmentNotFoundException;
	
	AppointmentDTO bookAppointment(AppointmentDTO appointmentDTO);

	List<AppointmentDTO> getAllUpcomingAppointments(int userId);
	
	List<AppointmentDTO> getUpcomingSchedule(int lifeCoachId) ;
}
