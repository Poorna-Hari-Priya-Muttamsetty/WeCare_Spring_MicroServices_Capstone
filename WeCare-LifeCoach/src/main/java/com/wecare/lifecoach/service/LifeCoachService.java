package com.wecare.lifecoach.service;

import java.util.List;

import com.wecare.lifecoach.dto.LifeCoachDTO;
import com.wecare.lifecoach.exception.LifeCoachNotFoundException;

public interface LifeCoachService {

	LifeCoachDTO signUp(LifeCoachDTO lifeCoachDTO);
	
	String login(String username, String password) throws LifeCoachNotFoundException;
	
	LifeCoachDTO getLifeCoachProfile(int lifeCoachId) throws LifeCoachNotFoundException;
		
	List<LifeCoachDTO> getAllLifeCoaches();

}
