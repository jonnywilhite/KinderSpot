package com.ex.domain;

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
@Table (name = "EDU_PHOTOS")
public class Photos {
	@Id
	@Column(name = "P_ID")
	@GeneratedValue
//	@SequenceGenerator(allocationSize = 1, name ="photoSeq", sequenceName ="PHOTO_SEQUENCE")
//	@GeneratedValue (strategy=GenerationType.SEQUENCE, generator = "photoSeq")
	private int id;
	
	@Column(name ="P_PHOTO")
	private String photo;
	
	@ManyToOne
	@JoinColumn(name="e_id")
	private Event event;
	
	public Photos (){}

	public Photos(int id, String photo, Event event) {
		super();
		this.id = id;
		this.photo = photo;
		this.event = event;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
