package com.ex.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EDU_BADGE_STUDENT")
public class BadgeStudent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="e_id")
	private int bId;
	
	@Id
	@Column(name="s_id")
	private int sId;
	
	

	public BadgeStudent(int bId, int sId) {
		super();
		this.bId = bId;
		this.sId = sId;
	}

	public BadgeStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

}
