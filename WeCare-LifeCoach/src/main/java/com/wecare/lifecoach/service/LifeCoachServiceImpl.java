package com.wecare.lifecoach.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.wecare.lifecoach.dto.LifeCoachDTO;
import com.wecare.lifecoach.entity.LifeCoach;
import com.wecare.lifecoach.exception.LifeCoachNotFoundException;
import com.wecare.lifecoach.repository.LifeCoachRepository;
import com.wecare.lifecoach.util.LifeCoachConstants;

@Service
@PropertySource("ValidationMessages.properties")
public class LifeCoachServiceImpl implements LifeCoachService{

	@Autowired
	private LifeCoachRepository lifeCoachRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Environment environment;

	@Override
	public LifeCoachDTO signUp(LifeCoachDTO lifeCoachDTO) {
		lifeCoachRepository.saveAndFlush(modelMapper.map(lifeCoachDTO, LifeCoach.class));
		return lifeCoachDTO;
	}

	@Override
	public String login(String username, String password) throws LifeCoachNotFoundException {
		LifeCoach coach = lifeCoachRepository.findByName(username);
		if(coach != null) {
			if(coach.getPassword().equals(password))
				return environment.getProperty(LifeCoachConstants.LIFECOACH_LOGIN_SUCCESS.toString());
			else
				return environment.getProperty(LifeCoachConstants.LIFECOACH_LOGIN_FAILED.toString());
		} else {
			throw new LifeCoachNotFoundException(environment.getProperty(LifeCoachConstants.LIFECOACH_NOT_FOUND.toString()));
		}
	}

	@Override
	public LifeCoachDTO getLifeCoachProfile(int lifeCoachId) throws LifeCoachNotFoundException {
		Optional<LifeCoach> lifeCoachOp = lifeCoachRepository.findById(lifeCoachId);
		if(lifeCoachOp.isPresent()) {
			return modelMapper.map(lifeCoachOp.get(), LifeCoachDTO.class);
		} else {
			throw new LifeCoachNotFoundException(environment.getProperty(LifeCoachConstants.LIFECOACH_NOT_FOUND.toString()));
		}
	}
	
	@Override
	public List<LifeCoachDTO> getAllLifeCoaches() {
		List<LifeCoach> lifeCoach = lifeCoachRepository.findAll();
		List<LifeCoachDTO> lifeCoaches = new ArrayList<>();
		for(LifeCoach lc : lifeCoach){
			lifeCoaches.add(modelMapper.map(lc,LifeCoachDTO.class));
		}
		return lifeCoaches;
	}

}
