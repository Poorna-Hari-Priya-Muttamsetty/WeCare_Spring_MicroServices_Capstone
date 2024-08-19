package com.wecare.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
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

import com.wecare.user.dto.LifeCoachDTO;
import com.wecare.user.dto.UserDTO;
import com.wecare.user.exception.UserNotFoundException;
import com.wecare.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@EnableFeignClients
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserCoachFeign userCoachFeign;
	
	@PostMapping("/signUp")
	public ResponseEntity<UserDTO> signUp(@Valid @RequestBody UserDTO userDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(userDTO));
	}

	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam("user") String username, @RequestParam("pwd") String password) throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.login(username, password));
	}

	@GetMapping("/userDetails/{userId}")
	public ResponseEntity<UserDTO> getUserProfile(@PathVariable("userId") int userId) throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserProfile(userId));
	}

	@GetMapping("/allCoaches")
	public ResponseEntity<List<LifeCoachDTO>> getAllLifeCoaches() {
		return ResponseEntity.status(HttpStatus.OK).body(userCoachFeign.getAllLifeCoaches());
	}
}
