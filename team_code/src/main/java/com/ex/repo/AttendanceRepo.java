package com.ex.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ex.domain.Attendance;
import com.ex.domain.Meetings;
import com.ex.domain.User;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Integer>{
	
	List<Attendance> findByTeacher(User teacher);
	Attendance createAttendance (Attendance attendance);
}
