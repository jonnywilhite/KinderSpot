package com.ex.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EDU_ATTENDANCE")
public class Attendance 
{
	@Id
	@Column(name="A_ID")
	@SequenceGenerator(allocationSize=1, name="attendanceSequence", sequenceName="attendance_sequence")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="attendanceSequence")
	private int id;
	
	@Column(name="A_DATE")
	private Timestamp date;
	
	@ManyToOne
	@JoinColumn(name="T_ID")
	private User teacher;
	
	@OneToMany(mappedBy="teacher")
	private List<Student> students;
	
	public Attendance(){}

	public Attendance(int id, Timestamp date, User teacher, List<Student> students) {
		super();
		this.id = id;
		this.date = date;
		this.teacher = teacher;
		this.students = students;
	}

	//Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
