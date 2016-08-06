package com.ex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.Student;
import com.ex.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@RequestMapping(value="students/{teacherId}", method=RequestMethod.GET)
	public List<Student> getStudentsInClass(@PathVariable int teacherId) {
		return service.getAllStudentsByTeacher(teacherId);
	}

}
