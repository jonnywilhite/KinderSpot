package com.ex.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EDU_ATTENDANCE_STUDENT")
public class AttendanceStudent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8909459415030833362L;

	@Id
	@ManyToOne
	@JoinColumn(name="A_ID")
	private Attendance attendance;
	
	@Id
	@ManyToOne
	@JoinColumn(name="s_id")
	private Student student;
	
	@Column(name="as_present")
	private boolean present;
	
	public AttendanceStudent(){}
	
	public AttendanceStudent(Attendance attendance, Student student, boolean present) {
		super();
		this.attendance = attendance;
		this.student = student;
		this.present = present;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
