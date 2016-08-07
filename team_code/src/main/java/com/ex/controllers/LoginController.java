package com.ex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.Student;
import com.ex.domain.User;
import com.ex.service.KinderService;

@RestController
public class LoginController {
	
	@Autowired
	private KinderService service;
	
	@RequestMapping(value="home", method=RequestMethod.POST)
	public User logIn(@RequestBody User user) {
		return service.authenticate(user);
	}
	
	//Get user (parent/teacher) by email (email must be unique, so it's same as ID).
	/*Currently does not handle what to do if there are multiple users with the same email & pass, but that
	  shouldn't ever happen to begin with if we set email to be unique (not done as of now)*/
	//User @PathVariable for passing data in the url path. 
	//For whatever reason, it won't work with two back-to-back {} variables, so put "login" in middle.
	/*Don't forget to use @PathVariables on each parameter, and make sure the @PathVariable names match
	  the parameters inside the function! */
	
	/*
	
	@RequestMapping(value="{userEmail}/login/{userPassword}", method=RequestMethod.POST)
	public List<User> getUserByEmailAndPassword(@PathVariable String userEmail, @PathVariable String userPassword) 
	{
		return service.getUserByEmailAndPassword(userEmail, userPassword);
	}
	
	*/

}












