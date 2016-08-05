package com.ex.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EDU_BADGES")
public class Badge {
	@Id
	@Column(name="b_id")
	@SequenceGenerator(allocationSize=1, name="badgeSequence", sequenceName="badge_sequence")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="badgeSequence")
	private int id;
	
	@Column(name="b_description")
	private String description;
	
	@Column(name="b_photo")
	private String photo;
	
	

	public Badge(int id, String description, String photo) {
		super();
		this.id = id;
		this.description = description;
		this.photo = photo;
	}

	public Badge() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
