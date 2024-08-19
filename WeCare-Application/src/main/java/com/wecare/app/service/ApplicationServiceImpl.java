package com.wecare.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.wecare.app.dto.AppointmentDTO;
import com.wecare.app.entity.Appointment;
import com.wecare.app.exception.AppointmentNotFoundException;
import com.wecare.app.repository.AppointmentRepository;
import com.wecare.app.util.AppointmentConstants;



@Service
public class ApplicationServiceImpl implements ApplicationService{

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Environment environment;
	
	@Override
	public AppointmentDTO rescheduleAppointment(int appointmentId, LocalDateTime date) throws AppointmentNotFoundException {
		Optional<Appointment> appointmentOp = appointmentRepository.findById(appointmentId);
		Appointment apppointment = null;
		if(appointmentOp.isPresent()) {
			apppointment = appointmentOp.get();
			apppointment.setAppointmentDate(date);
			appointmentRepository.saveAndFlush(modelMapper.map(apppointment,Appointment.class));
			return modelMapper.map(apppointment, AppointmentDTO.class);
		} else {
			throw new AppointmentNotFoundException(environment.getProperty(AppointmentConstants.APPPOINTMENT_NOT_FOUND.toString()));
		}
	}

	@Override
	public AppointmentDTO cancelAppointment(int appointmentId) throws AppointmentNotFoundException{
		Optional<Appointment> appointmentOp = appointmentRepository.findById(appointmentId);
		Appointment apppointment = null;
		if(appointmentOp.isPresent()) {
			apppointment = appointmentOp.get();
			appointmentRepository.delete(modelMapper.map(apppointment,Appointment.class));
			return modelMapper.map(apppointment, AppointmentDTO.class);
		} else {
			throw new AppointmentNotFoundException(environment.getProperty(AppointmentConstants.APPPOINTMENT_NOT_FOUND.toString()));
		}	
	}
	
	@Override
	public AppointmentDTO bookAppointment(AppointmentDTO appointmentDTO){
		appointmentRepository.save(modelMapper.map(appointmentDTO,Appointment.class));
		return appointmentDTO;
	}

	@Override
	public List<AppointmentDTO> getAllUpcomingAppointments(int userId) {
		List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
		List<Appointment> appointments = appointmentRepository.findAllUpcomingAppointmentsForUser(userId);
		for(Appointment app : appointments) {
			appointmentDTOs.add(modelMapper.map(app,AppointmentDTO.class));
		}
		return appointmentDTOs;
	}
	
	@Override
	public List<AppointmentDTO> getUpcomingSchedule(int lifeCoachId) {
		List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
		List<Appointment> appointments = appointmentRepository.findAllUpcomingAppointmentsForLifeCoach(lifeCoachId);
		for(Appointment app : appointments) {
			appointmentDTOs.add(modelMapper.map(app,AppointmentDTO.class));
		}
		return appointmentDTOs;
	}
	
}
