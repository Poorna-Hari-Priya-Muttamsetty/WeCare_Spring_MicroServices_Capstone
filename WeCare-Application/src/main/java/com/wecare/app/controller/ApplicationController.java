package com.wecare.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecare.app.dto.AppointmentDTO;
import com.wecare.app.dto.LifeCoachDTO;
import com.wecare.app.dto.UserDTO;
import com.wecare.app.exception.AppointmentNotFoundException;
import com.wecare.app.exception.LifeCoachNotFoundException;
import com.wecare.app.exception.UserNotFoundException;
import com.wecare.app.service.ApplicationService;
import com.wecare.app.util.AppointmentConstants;

import jakarta.validation.Valid;


@RestController
@Validated
@RequestMapping("/app")
public class ApplicationController {

	@Autowired
	private ApplicationService appointmentService;
	
	@Autowired
	private AppUserFeign appUserFeign;
	
	@Autowired
	private AppCoachFeign appCoachFeign;
	
	@PutMapping("/rescheduleAppointment/{id}/{date}")
	public ResponseEntity<AppointmentDTO> rescheduleAppointment(@PathVariable("id") int appointmentId, @PathVariable("date") LocalDateTime date) throws AppointmentNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.rescheduleAppointment(appointmentId, date));
	}

	@DeleteMapping("/deleteAppointment/{id}")
	public ResponseEntity<AppointmentDTO> cancelAppointment(@PathVariable("id") int appointmentId) throws AppointmentNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.cancelAppointment(appointmentId));
	}
	
	@PostMapping("/saveAppointment")
	public ResponseEntity<AppointmentDTO> bookAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO) throws UserNotFoundException, LifeCoachNotFoundException {
		UserDTO user = appUserFeign.getUserDetails(appointmentDTO.getUserId());
		if(user == null) {
			throw new UserNotFoundException(AppointmentConstants.USER_NOT_FOUND.toString());
		}
		LifeCoachDTO coach = appCoachFeign.getCoachDetails(appointmentDTO.getLifeCoachId());
		if(coach == null) {
			throw new LifeCoachNotFoundException(AppointmentConstants.LIFECOACH_NOT_FOUND.toString());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.bookAppointment(appointmentDTO));
	}

	@GetMapping("/allUpcomingAppointments/{id}")
	public ResponseEntity<List<AppointmentDTO>> getAllUpcomingAppointments(@PathVariable("id") int userId) throws UserNotFoundException {
		UserDTO user = appUserFeign.getUserDetails(userId);
		if(user == null) {
			throw new UserNotFoundException(AppointmentConstants.USER_NOT_FOUND.toString());
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllUpcomingAppointments(userId));
	}
	
	@GetMapping("/upcomingSchedules/{coachId}")
	public ResponseEntity<List<AppointmentDTO>> getUpcomingSchedule(@PathVariable("coachId") int lifeCoachId) throws LifeCoachNotFoundException {
		LifeCoachDTO coach = appCoachFeign.getCoachDetails(lifeCoachId);
		if(coach == null) {
			throw new LifeCoachNotFoundException(AppointmentConstants.LIFECOACH_NOT_FOUND.toString());
		}
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getUpcomingSchedule(lifeCoachId));
	}
	
}
