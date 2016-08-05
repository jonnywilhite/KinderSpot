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
@Table(name="EDU_ATTENDANCE_STUDENT")
public class AttendanceStudent 
{
	@Id
	@Column(name="A_ID")
	private int a_id;
	
	@Column(name="s_id")
	private int s_id;
	
	@Column(name="as_present")
	private double present;
	
	public AttendanceStudent(){}
	
	
	public AttendanceStudent(int a_id, int s_id, double present)
	{
		super();
		this.a_id = a_id;
		this.s_id = s_id;
		this.present = present;
	}
	
	
	
	//Getters & Setters
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public double getPresent() {
		return present;
	}
	public void setPresent(double present) {
		this.present = present;
	}
	
	
}
