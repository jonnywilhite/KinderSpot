package com.ex.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class Event {
	@Id
	@Column(name="e_id")
	@SequenceGenerator(allocationSize=1, name="eventSequence", sequenceName="event_sequence")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="eventSequence")
	private int id;
	
	@Column(name="e_name")
	private String name;
	
	@Column(name="e_description")
	private String description;
	
	@Column(name="e_date")
	private Timestamp date;
	
	@Column(name="et_id")
	private int typeId;
	
	public Event(int id, String name, String description, Timestamp date, int typeId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.typeId = typeId;
	}
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
}
