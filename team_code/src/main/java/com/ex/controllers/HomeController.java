package com.ex.controllers;

import java.util.ArrayList;
import java.util.List;

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
public class HomeController 
{
	@Autowired
	private KinderService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getHome(HttpServletRequest req, HttpServletResponse resp)
	{
		return "FrontPage";
	}
	
	
}









