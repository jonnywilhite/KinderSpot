package com.ex.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EDU_BADGE_STUDENT")
public class BadgeStudent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@ManyToOne
	@JoinColumn(name="b_id")
	private Badge badge;
	
	@Id
	@ManyToOne
	@JoinColumn(name="s_id")
	private Student student;
	
	public BadgeStudent() {}

	public BadgeStudent(Badge badge, Student student) {
		super();
		this.badge = badge;
		this.student = student;
	}

	public Badge getBadge() {
		return badge;
	}

	public void setBadge(Badge badge) {
		this.badge = badge;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
