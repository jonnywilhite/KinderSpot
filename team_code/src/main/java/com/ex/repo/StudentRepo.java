package com.ex.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.Student;
import com.ex.domain.User;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	List<Student> findByParentAndActiveTrue(User user);
	List<Student> findByTeacherAndActiveTrue(User user);
	List<Student> findAll();
}
