package com.ex.service;


import java.io.File;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.ex.domain.Attendance;
import com.ex.domain.AttendanceStudent;
import com.ex.domain.Event;
import com.ex.domain.Meetings;
import com.ex.domain.Photos;
import com.ex.domain.ReportCard;
import com.ex.domain.Student;
import com.ex.domain.User;

public interface KinderService {
	
	
	//Student stuff
	List<Student> getAllStudents();
	Student getStudentById(int studentId);
	List<Student> getStudentByParent(int parentId);
	List<Student> getAllStudentsByTeacher(int teacherId);
	List<Student> deleteStudentsInClassByTeacher(int teacherId);
	List<Student> deleteStudentsInClassByTeacher(int teacherId, int[] studentIds);
	
	
	//ReportCard stuff
	ReportCard createReportCardEntry(ReportCard rc);
	List<ReportCard> getAllReportCardsByTeacher(int teacherId);
	ReportCard getReportCardByStudent(int studentId);
	
	
	//Event stuff
	public  List <Event> getAllEvents ();
	public Event createEvent (Event event);
	public Event getEventByEventName (String name);
	public Event deleteEvent (String name);
	public Event updateEvent (Event event, @PathVariable String eventName);
	
	
	//Meeting Stuff 
	public Meetings createMeeting (Meetings meeting);
	public List <Meetings> getAllMeetings ();
	public Meetings updateMeetingStatus(Meetings meeting, @PathVariable Boolean meetingStatus);
	
	
	
	//Login stuff
	User authenticate(User user);
	User findById(int id);
	
	//Photos stuff
	Photos uploadPhoto(Photos photo, File file);
	List<Photos> getAllPhotos();
	List<Photos> getPhotosByEvent(int eventId);


	//Attendance stuff
	public Attendance submitAttendanceSheet(List<Student> absent);
	public List<Attendance> viewAttendanceSheets(int teacherId);
	public List<AttendanceStudent> viewAttendanceEntriesByStudent(int studentId);
	
	//Email stuff
	public void sendEmail(int senderId, int recipientId, String subject, String body);
	public void emailAllParents(int teacherId, String subject, String body);
	
	
}
