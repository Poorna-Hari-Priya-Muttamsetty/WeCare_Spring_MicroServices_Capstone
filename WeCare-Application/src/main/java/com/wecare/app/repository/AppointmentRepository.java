package com.wecare.app.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wecare.app.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate > CURRENT_TIMESTAMP AND a.user_id = ?1")
    List<Appointment> findAllUpcomingAppointmentsForUser(int userId);

    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate > CURRENT_TIMESTAMP AND a.life_coach_id = ?1")
    List<Appointment> findAllUpcomingAppointmentsForLifeCoach(int lifeCoachId);

}
