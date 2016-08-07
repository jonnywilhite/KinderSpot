package com.ex.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex.domain.Attendance;
import com.ex.domain.Event;
import com.ex.domain.ReportCard;
import com.ex.domain.Student;
import com.ex.domain.User;
import com.ex.repo.AttendanceRepo;
import com.ex.repo.ReportCardRepo;
import com.ex.repo.StudentRepo;
import com.ex.repo.TeacherRepo;
import com.ex.repo.UserRepo;

@Service
@Transactional
public class KinderServiceImpl implements KinderService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private TeacherRepo teacherRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	ReportCardRepo reportCardRepo;
	
	@Autowired
	AttendanceRepo attendanceRepo;

	//Student stuff
	@Override
	public List<Student> getAllStudentsByTeacher(int teacherId) {
		User teacher = teacherRepo.findById(teacherId);
		return studentRepo.findByTeacherAndActiveTrue(teacher);
	}
	
	@Override
	public List<Student> deleteStudentsInClassByTeacher(int teacherId) {
		User teacher = teacherRepo.findById(teacherId);
		List<Student> students = studentRepo.findByTeacherAndActiveTrue(teacher);
		for (Student s : students) {
			s.setActive(false);
		}
		return students;
	}

	@Override
	public List<Student> deleteStudentsInClassByTeacher(int teacherId, int[] studentIds) {
		User teacher = teacherRepo.findById(teacherId);
		List<Student> students = studentRepo.findByTeacherAndActiveTrue(teacher);
		for (Student s : students) {
			for (int id : studentIds) {
				if (s.getId() == id) {
					s.setActive(false);
					break;
				}
			}
		}
		return students;
	}
	
	
	//Login stuff
	@Override
	public User authenticate(User user) {
		return userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}
	
	
	//ReportCard stuff
	@Override
	public ReportCard createReportCardEntry(ReportCard rc) {
		rc.setDate(new Timestamp(new Date().getTime()));
		return reportCardRepo.save(rc);
	}
	
	@Override
	public List<ReportCard> getAllReportCardsByTeacher(int teacherId) {
		User teacher = teacherRepo.findOne(teacherId);
		return reportCardRepo.findByTeacher(teacher);
	}

	
	

	//Event stuff
	@Override
	public Page<Event> getEventpage(Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event deleteEvent(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event updateEvent(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//Attendance stuff
	@Override
	public Attendance submitAttendanceSheet(List<Student> absent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Attendance> viewAttendanceSheets(int teacherId) {
		User teacher = teacherRepo.findOne(teacherId);
		return attendanceRepo.findByTeacher(teacher);
	}

}
