package com.wecare.app.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wecare.app.dto.UserDTO;

@FeignClient(name = "app-user-client", url="http://localhost:1112/WeCare/user")
public interface AppUserFeign {

	@RequestMapping("/userDetails/{userId}")
	UserDTO getUserDetails(@PathVariable("userId") int user_id);
}
