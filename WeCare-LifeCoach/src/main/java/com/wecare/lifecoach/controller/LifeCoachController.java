package com.wecare.lifecoach.controller;


import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wecare.lifecoach.dto.LifeCoachDTO;
import com.wecare.lifecoach.exception.LifeCoachNotFoundException;

@RestController
@RequestMapping("/lifeCoach")
@Validated
public class LifeCoachController {

	@Autowired
	private com.wecare.lifecoach.service.LifeCoachService lifeCoachService;
	
	@PostMapping("/signup")
	public ResponseEntity<LifeCoachDTO> signUp(@Valid @RequestBody LifeCoachDTO lifeCoachDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(lifeCoachService.signUp(lifeCoachDTO));
	}

	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam("coach") String username,@RequestParam("pwd") String password) throws LifeCoachNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(lifeCoachService.login(username, password));
	}

	@GetMapping("/coachDetails/{coachId}")
	public ResponseEntity<LifeCoachDTO> getLifeCoachProfile(@PathVariable("coachId") int lifeCoachId) throws LifeCoachNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(lifeCoachService.getLifeCoachProfile(lifeCoachId));
	}


	@GetMapping("/allCoaches")
	public ResponseEntity<List<LifeCoachDTO>> getAllLifeCoaches() {
		return ResponseEntity.status(HttpStatus.OK).body(lifeCoachService.getAllLifeCoaches());
	}
}
