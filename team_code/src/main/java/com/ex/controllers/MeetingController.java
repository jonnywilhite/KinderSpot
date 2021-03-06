package com.ex.controllers;

import java.util.List;

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
 	
 	@RequestMapping (value ="meeting/{parentId}", method = RequestMethod.POST)
 	public Meetings createMeeting(@RequestBody Meetings meeting, @PathVariable int parentId){
 		return service.createMeeting(meeting, parentId, meeting.getTeacher());
 	}
 	
 	
 	@RequestMapping (value= "parentMeeting/{parentId}", method = RequestMethod.GET)
 	public List<Meetings> getMeetingsByParent(@PathVariable int parentId) {
 		return service.getMeetingsByParent(parentId);
 	}
 	
 	@RequestMapping (value= "meeting/{teacherId}", method = RequestMethod.GET)
 	public List<Meetings> getAllMeetings(@PathVariable int teacherId) {
 		return service.getAllMeetings(teacherId);
 	}
 	
 	@RequestMapping(value = "meeting/{meetingStatus}", method = RequestMethod.PUT)
 	public Meetings updateMeetingStatus(@RequestBody Meetings meeting, @PathVariable Boolean meetingStatus){
 		return service.updateMeetingStatus(meeting, meetingStatus);
 	}
 }
