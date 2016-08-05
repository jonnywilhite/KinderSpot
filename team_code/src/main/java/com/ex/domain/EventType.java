package com.ex.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EDU_EVENT_TYPES")
public class EventType {
	@Id
	@Column(name="et_id")
	private int id;
	
	@Column(name="et_name")
	private String name;
	
	
	
	public EventType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public EventType() {
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
}
