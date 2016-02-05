package com.persistent.securityPractice.armature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.persistent.securityPractice.armature.dto.User;
import com.persistent.securityPractice.armature.service.UserService;
import com.persistent.securityPractice.armature.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/users")
public class UserController {
	private UserService userService;

	@Autowired(required=true)
	public void setUserService(UserServiceImpl userServiceImpl) {
		this.userService = userServiceImpl;
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST,  produces = "application/json")
	public @ResponseBody
		User addUser(@RequestBody User user, HttpServletRequest request,
			HttpSession session) {
		user = userService.registerUser(user);
		
		return user;
	}

	@RequestMapping(value = "/{userName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	User getUser(@PathVariable("userName") String userName,
			HttpServletRequest request, HttpSession session) {
		User user = userService.getUserByUserName(userName);
		return user;
	}

}
