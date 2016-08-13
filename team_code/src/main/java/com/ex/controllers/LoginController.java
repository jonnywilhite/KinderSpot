package com.ex.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.User;
import com.ex.service.KinderService;

@RestController
public class LoginController {
	
	@Autowired
	private KinderService service;
	
	@RequestMapping(value="home", method=RequestMethod.POST)
	public User logIn(@RequestBody User user, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		User myUser = service.authenticate(user);
		req.getSession().setAttribute("loggedInUser", myUser); //Saves User into session for later reference.
		return myUser;
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












