package com.ex.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.Attendance;
import com.ex.domain.AttendanceStudent;
import com.ex.domain.Student;

public interface AttendanceStudentRepo extends JpaRepository<AttendanceStudent, Integer> {
	
	List<AttendanceStudent> findByStudentOrderByAttendance(Student student);
	List<AttendanceStudent> findByAttendance(Attendance attendance);

}
