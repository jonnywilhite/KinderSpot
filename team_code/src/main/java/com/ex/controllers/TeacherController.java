package com.ex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.Photos;
import com.ex.service.KinderService;

@RestController
public class TeacherController {
	
	@Autowired
	private KinderService service;
	
	@RequestMapping(value="photos", method=RequestMethod.POST)
	public Photos uploadPhoto(@RequestBody Photos photo) {
		return null;
	}

}
