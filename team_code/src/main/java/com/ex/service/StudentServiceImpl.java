package com.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex.domain.Student;
import com.ex.domain.User;
import com.ex.repo.StudentRepo;
import com.ex.repo.TeacherRepo;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private TeacherRepo teacherRepo;

	@Override
	public List<Student> getAllStudentsByTeacher(int teacherId) {
		User teacher = teacherRepo.findById(teacherId);
		return studentRepo.findByTeacher(teacher);
	}

}
