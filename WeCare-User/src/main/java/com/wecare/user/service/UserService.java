package com.wecare.user.service;
import com.wecare.user.dto.UserDTO;
import com.wecare.user.exception.UserNotFoundException;

public interface UserService {

	com.wecare.user.dto.UserDTO signUp(UserDTO userDTO);
	
	String login(String username, String password) throws UserNotFoundException;
	
	UserDTO getUserProfile(int userId) throws UserNotFoundException;
	
}
