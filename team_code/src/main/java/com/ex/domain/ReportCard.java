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
@Table(name ="EDU_REPORT_CARD")
public class ReportCard {
	
	@Id
	@Column(name = "RC_ID")
	@SequenceGenerator(allocationSize = 1, name ="rcSeqGen", sequenceName ="RC_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="rcSeqGen")
	private int id;
	
	@Column(name = "RC_GRADE")
	private String grade; 
	
	@Column (name ="RC_DATE")
	private Timestamp date;
	
	

	public ReportCard (){}
	
	
	public ReportCard(int id, String grade, Timestamp date) {
		super();
		this.id = id;
		this.grade = grade;
		this.date = date;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	

}