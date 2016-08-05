package com.ex.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EDU_EVENT_STUDENT")
public class EventStudent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="e_id")
	private int eId;
	
	@Id
	@Column(name="s_id")
	private int sId;
	
	
	public EventStudent(int eId, int sId) {
		super();
		this.eId = eId;
		this.sId = sId;
	}
	public EventStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int geteId() {
		return eId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
}
