package com.ex.controllers;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.Meetings;
import com.ex.service.KinderService;

@RestController
public class MeetingController {

	@Autowired
	private KinderService service;
	
	@RequestMapping (value ="meeting", method = RequestMethod.POST)
	public Meetings createMeeting(Meetings meeting){
		return service.createMeeting(meeting);
	}
	
	@RequestMapping (value= "meeting", method = RequestMethod.GET)
	public Meetings getMeetingByDate(Timestamp date) {
		return service.getMeetingByDate(date);
	}
	
	@RequestMapping(value = "meeting/{meetingStatus}", method = RequestMethod.PUT)
	public Meetings updateMeetingStatus(@RequestBody Meetings meeting, @PathVariable Boolean meetingStatus){
		return null; 
	}
}
