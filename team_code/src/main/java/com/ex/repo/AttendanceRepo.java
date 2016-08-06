package com.ex.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ex.domain.Attendance;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Integer>{
	
	

}
