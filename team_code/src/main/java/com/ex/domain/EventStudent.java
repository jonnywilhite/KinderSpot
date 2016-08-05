package com.ex.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EDU_EVENT_STUDENT")
public class EventStudent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@ManyToOne
	@JoinColumn(name="e_id")
	private Event event;
	
	@Id
	@ManyToOne
	@JoinColumn(name="s_id")
	private Student student;
	
	
	
	public EventStudent(Event event, Student student) {
		super();
		this.event = event;
		this.student = student;
	}
	public EventStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
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
