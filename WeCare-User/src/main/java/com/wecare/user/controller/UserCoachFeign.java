package com.wecare.user.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wecare.user.dto.LifeCoachDTO;

@FeignClient(name = "user-coach-client", url = "http://localhost:1113/WeCare/lifeCoach")
public interface UserCoachFeign {

	@RequestMapping("/allCoaches")
	List<LifeCoachDTO> getAllLifeCoaches();
}
