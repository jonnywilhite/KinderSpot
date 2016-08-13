package com.ex.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.BadgeStudent;
import com.ex.domain.Student;

public interface BadgeStudentRepo extends JpaRepository<BadgeStudent, Integer> {
	
	List<BadgeStudent> findByStudent(Student student);

}
