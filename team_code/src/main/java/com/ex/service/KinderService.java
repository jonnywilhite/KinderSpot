package com.ex.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ex.domain.Attendance;
import com.ex.domain.Event;
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
	public Event deleteEvent (String name);
	public Event updateEvent (String name);
	
	
	//Login stuff
	User authenticate(User user);

	//Attendance stuff
	public Attendance submitAttendanceSheet(List<Student> absent);
	public List<Attendance> viewAttendanceSheets(int teacherId);
}
