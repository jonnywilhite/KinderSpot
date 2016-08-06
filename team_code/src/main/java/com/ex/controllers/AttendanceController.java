package com.ex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.Attendance;
import com.ex.service.KinderService;

@RestController
public class AttendanceController {
	
	@Autowired
	private KinderService service;
	
	@RequestMapping(value="{teacherId}/attendance", method=RequestMethod.GET)
	public List<Attendance> viewAllAttendanceSheets(@PathVariable int teacherId) {
		return service.viewAttendanceSheets(teacherId);
	}

}
