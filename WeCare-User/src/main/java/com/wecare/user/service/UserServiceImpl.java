package com.wecare.user.service;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.wecare.user.dto.UserDTO;
import com.wecare.user.entity.User;
import com.wecare.user.exception.UserNotFoundException;
import com.wecare.user.repository.UserRepository;
import com.wecare.user.util.UserConstants;

@Service
@PropertySource("ValidationMessages.properties")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Environment environment;

	@Override
	public UserDTO signUp(UserDTO userDTO) {
		userRepository.saveAndFlush(modelMapper.map(userDTO,User.class));
		return userDTO;
	}

	@Override
	public String login(String username, String password) throws UserNotFoundException {
		User user = userRepository.findByUserName(username);
		if(user != null) {
			if(user.getPassword().equals(password))
				return environment.getProperty(UserConstants.USER_LOGIN_SUCCESS.toString());
			else
				return environment.getProperty(UserConstants.USER_LOGIN_FAILED.toString());
		} else {
			throw new UserNotFoundException(environment.getProperty(UserConstants.USER_NOT_FOUND.toString()));
		}
	}

	@Override
	public UserDTO getUserProfile(int userId) throws UserNotFoundException {
		Optional<User> userOp = userRepository.findById(userId);
		if(userOp.isPresent()) {
			return modelMapper.map(userOp.get(), UserDTO.class);
		} else {
			throw new UserNotFoundException(environment.getProperty(UserConstants.USER_NOT_FOUND.toString()));
		}	
	}
}
