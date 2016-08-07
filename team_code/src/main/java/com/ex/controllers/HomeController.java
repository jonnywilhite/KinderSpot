package com.ex.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.User;
import com.ex.service.KinderService;

@RestController
public class HomeController 
{
	//Set up to work with "http://localhost:8085/KinderSpot". The htmls are all placed in "webapp/resources"
	@RequestMapping(value="/", method=RequestMethod.GET)
	public void getHome(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		resp.sendRedirect("resources/pages/FrontPage.html");
		
		/*If you want to use the old way to redirect to a .jsp, it's configured in the beans.xml to still
		  work with that technique. Just replace "@RestController" with "@Controller", replace "void" return
		  to "String", and instead of the above code, use "return 'jspName'. */
	}
	
	
}









