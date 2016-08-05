package com.ex.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column(name="T_ID")
	private int t_id;
	
	public Attendance(){}
	
	public Attendance(int id, Timestamp date, int t_id)
	{
		super();
		this.id = id;
		this.date = date;
		this.t_id = t_id;
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

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	
	
	
}
