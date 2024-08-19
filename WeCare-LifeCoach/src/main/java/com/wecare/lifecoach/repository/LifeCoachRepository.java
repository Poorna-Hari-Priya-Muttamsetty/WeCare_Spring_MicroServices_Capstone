package com.wecare.lifecoach.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecare.lifecoach.entity.LifeCoach;


public interface LifeCoachRepository extends JpaRepository<LifeCoach, Integer>{

    LifeCoach findByName(String name);
}
