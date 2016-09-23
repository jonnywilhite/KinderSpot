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

}












