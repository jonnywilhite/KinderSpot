package com.ex.domain;

import java.sql.Timestamp;

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
@Table(name ="EDU_REPORT_CARD")
public class ReportCard implements Comparable<ReportCard> {
	
	@Id
	@Column(name = "RC_ID")
	@GeneratedValue
//	@SequenceGenerator(allocationSize = 1, name ="rcSeqGen", sequenceName ="RC_SEQUENCE")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="rcSeqGen")
	private int id;
	
	@Column(name = "RC_GRADE")
	private String grade; 
	
	@Column (name ="RC_DATE")
	private Timestamp date;
	
	@Column(name="RC_COMMENTS")
	private String comments;
	
	@ManyToOne
	@JoinColumn(name="t_id")
	private User teacher;
	
	@ManyToOne
	@JoinColumn(name="s_id")
	private Student student;
	

	public ReportCard (){}

	public ReportCard(int id, String grade, Timestamp date, String comments, User teacher, Student student) {
		super();
		this.id = id;
		this.grade = grade;
		this.date = date;
		this.comments = comments;
		this.teacher = teacher;
		this.student = student;
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

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int compareTo(ReportCard that) {
		return this.student.compareTo(that.student);
	}
	
	

}
