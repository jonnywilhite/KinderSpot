package com.ex.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.Event;
import com.ex.domain.EventStudent;
import com.ex.domain.Student;

public interface EventStudentRepo extends JpaRepository <EventStudent, Integer> 
{
	List<EventStudent> findByStudent(Student student);
	
	
}






