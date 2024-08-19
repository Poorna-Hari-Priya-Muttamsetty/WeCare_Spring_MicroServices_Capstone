package com.wecare.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_id")
    private int user_id;

    @Column(name="life_coach_id")
    private int life_coach_id;

	@Column(name = "appointment_time")
	private LocalDateTime appointmentDate;
    
    private int duration;

	public Appointment(int id, int userId, int lifeCoachId, LocalDateTime appointmentDate, int duration) {
		super();
		this.id = id;
		this.user_id = userId;
		this.life_coach_id = lifeCoachId;
		this.appointmentDate = appointmentDate;
		this.duration = duration;
	}

	public Appointment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUser(int user) {
		this.user_id = user;
	}

	public int getLifeCoach() {
		return life_coach_id;
	}

	public void setLifeCoach(int lifeCoach) {
		this.life_coach_id = lifeCoach;
	}

	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
    
}
