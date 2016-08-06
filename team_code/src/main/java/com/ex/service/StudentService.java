package com.ex.service;

import java.util.List;

import com.ex.domain.Student;

public interface StudentService {
	
	List<Student> getAllStudentsByTeacher(int teacherId);

}
