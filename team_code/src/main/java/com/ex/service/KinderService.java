package com.ex.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import com.ex.domain.Attendance;
import com.ex.domain.Event;
import com.ex.domain.Meetings;
import com.ex.domain.ReportCard;
import com.ex.domain.Student;
import com.ex.domain.User;

public interface KinderService {
	
	//Student stuff
	List<Student> getAllStudentsByTeacher(int teacherId);
	List<Student> deleteStudentsInClassByTeacher(int teacherId);
	List<Student> deleteStudentsInClassByTeacher(int teacherId, int[] studentIds);
	
	
	//ReportCard stuff
	ReportCard createReportCardEntry(ReportCard rc);
	List<ReportCard> getAllReportCardsByTeacher(int teacherId);
	
	
	//Event stuff
	public Page<Event> getEventpage (Integer page, Integer size);
	public Event getEventByEventName (String name);
	public Event createEvent (Event event);
	public Event updateEvent (Event event, @PathVariable String eventName);
	public Event deleteEvent (String name);

	
	//Meeting stuff
	public Meetings createMeeting (Meetings meeting);
	public Meetings getMeetingByDate (Timestamp date);
	public Meetings updateMeetingStatus(Meetings meeting, @PathVariable Boolean meetingStatus);
	
	
	//Login stuff
	User authenticate(User user);

	//Attendance stuff
	public Attendance submitAttendanceSheet(List<Student> absent);
	public List<Attendance> viewAttendanceSheets(int teacherId);
}
