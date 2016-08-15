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
@Table(name="EDU_EVENTS")
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
	
	@ManyToOne
	@JoinColumn(name="et_id")
	private EventType eventType;
	
	@ManyToOne
	@JoinColumn(name="t_id")
	private User teacher;
	
	
	public Event(int id, String name, String description, Timestamp date, EventType eventType) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.eventType = eventType;
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
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
