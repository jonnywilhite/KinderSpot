package com.ex.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EDU_ATTENDANCE_STUDENT")
public class AttendanceStudent implements Serializable, Comparable<AttendanceStudent> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8909459415030833362L;

	@Id
	@Column(name="as_id")
	@GeneratedValue
//	@SequenceGenerator(allocationSize=1, name="attendanceStudentSequence", sequenceName="as_sequence")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="attendanceStudentSequence")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="A_ID")
	private Attendance attendance;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendance == null) ? 0 : attendance.hashCode());
		result = prime * result + (present ? 1231 : 1237);
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "AttendanceStudent [id=" + id + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttendanceStudent other = (AttendanceStudent) obj;
		if (attendance == null) {
			if (other.attendance != null)
				return false;
		} else if (!attendance.equals(other.attendance))
			return false;
		if (present != other.present)
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	@Override
	public int compareTo(AttendanceStudent that) {
		
		return this.getAttendance().compareTo(that.getAttendance());
	}
	
	
	
}
