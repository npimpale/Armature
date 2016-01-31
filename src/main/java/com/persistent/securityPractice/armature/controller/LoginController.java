package com.persistent.securityPractice.armature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.persistent.securityPractice.armature.dto.User;
import com.persistent.securityPractice.armature.service.LoginService;
import com.persistent.securityPractice.armature.service.LoginServiceImpl;

@Controller
@SessionAttributes({ "user", "sessionId" })
public class LoginController {
	private LoginService loginService;
	
	@Autowired(required = true)
	public void setLoginService(LoginServiceImpl loginServiceImpl) {
		this.loginService = loginServiceImpl;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showIndexPage() {
		return "index";
	}

	@RequestMapping(value = "/login/authenticate", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody boolean loginUser(@RequestBody User user) {
		boolean success = false;
		success = loginService.authenticateUser(user.getUserName(), user.getHashedPassword());
		return success;
	}
	
	@RequestMapping(value = "/login/token", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody String getCSRFToken(@RequestBody User user) {
		String token = null;
		token = loginService.getCSRFToken(user.getUserName());
		return token;
	}
}
