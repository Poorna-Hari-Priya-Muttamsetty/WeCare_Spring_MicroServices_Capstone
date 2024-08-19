package com.wecare.app.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wecare.app.dto.LifeCoachDTO;

@FeignClient(name = "app-coach-client", url="http://localhost:1113/WeCare/lifeCoach")
public interface AppCoachFeign {

	@RequestMapping("/coachDetails/{coachId}")
	LifeCoachDTO getCoachDetails(@PathVariable("coachId") int coach_id);
}
