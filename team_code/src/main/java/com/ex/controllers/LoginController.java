package com.ex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.User;
import com.ex.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@RequestMapping(value="home", method=RequestMethod.POST)
	public User logIn(@RequestBody User user) {
		return service.authenticate(user);
	}

}
