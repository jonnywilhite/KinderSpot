package com.ex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.Student;
import com.ex.service.KinderService;

@RestController
public class StudentController {
	
	@Autowired
	private KinderService service;
	
	@RequestMapping(value="students/{studentId}", method=RequestMethod.GET)
	public Student getStudentById(@PathVariable int studentId) {
		return service.getStudentById(studentId);
	}
	
	@RequestMapping(value="{teacherId}/students", method=RequestMethod.GET)
	public List<Student> getStudentsInClass(@PathVariable int teacherId) {
		return service.getAllStudentsByTeacher(teacherId);
	}
	
	@RequestMapping(value="{teacherId}/students", method=RequestMethod.DELETE)
	public List<Student> deleteAllStudentsInClass(@PathVariable int teacherId) {	//For when students 'graduate' kindergarten
		return service.deleteStudentsInClassByTeacher(teacherId);
	}
	
	@RequestMapping(value="{teacherId}/students/{studentIds}", method=RequestMethod.DELETE)
	public List<Student> deleteSomeStudentsInClass(@PathVariable int teacherId, @PathVariable int[] studentIds) {	//For when students 'graduate' kindergarten
		return service.deleteStudentsInClassByTeacher(teacherId, studentIds);
	}

}
