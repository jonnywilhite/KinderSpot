package com.ex.controllers;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.Attendance;
import com.ex.domain.AttendanceStudent;
import com.ex.service.KinderService;

@RestController
public class AttendanceController {
	
	@Autowired
	private KinderService service;
	
	@RequestMapping(value="{teacherId}/attendance", method=RequestMethod.GET)
	public List<Attendance> viewAllAttendanceSheets(@PathVariable int teacherId) {
		List<Attendance> l = service.viewAttendanceSheets(teacherId);
		Collections.reverse(l);
		return l;
	}
	
	@RequestMapping(value="{teacherId}/attendance/{date}", method=RequestMethod.GET)
	public List<AttendanceStudent> viewAttendanceSheetForDate(@PathVariable int teacherId, @PathVariable long date) {
		return service.viewAttendanceSheetForDate(teacherId, new Date(date));
	}
	
	@RequestMapping(value="{teacherId}/attendance/{date}", method=RequestMethod.PUT)
	public Attendance updateAttendanceSheetForDate(@PathVariable int teacherId, @PathVariable long date, @RequestBody List<AttendanceStudent> attendanceSheet) {
		return service.updateAttendanceSheetForDate(teacherId, new Date(date), attendanceSheet);
	}
	
	@RequestMapping(value="attendance/{studentId}", method=RequestMethod.GET)
	public List<AttendanceStudent> viewAllAttendanceEntriesForStudent(@PathVariable int studentId) {
		return service.viewAttendanceEntriesByStudent(studentId);
	}
	
	@RequestMapping(value="{teacherId}/attendance", method=RequestMethod.POST)
	public Attendance submitAttendanceSheet(@RequestBody List<AttendanceStudent> attendanceSheet, @PathVariable int teacherId) {
		return service.submitAttendanceSheet(attendanceSheet, teacherId);
	}
	

}
