package com.ex.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="EDU_MEETINGS")
public class Meetings {
	@Id
	@Column(name ="M_ID")
	@SequenceGenerator(allocationSize = 1, name ="meetingSeq", sequenceName ="MEETING_SEQUENCE")
	@GeneratedValue (strategy=GenerationType.SEQUENCE, generator ="meetingSeq")
	private int id;
	
	@Column(name ="M_DATE")
	private Timestamp date;
	
	
	@Column(name ="M_DATE")
	private String reason;
	
	@Column(name ="M_T_APPROVED")
	private int tApprove;
	
	@Column(name ="M_P_APPROVED")
	private int pApprove;
	
	public Meetings (){}

	public Meetings(int id, Timestamp date, String reason, int tApprove, int pApprove) {
		super();
		this.id = id;
		this.date = date;
		this.reason = reason;
		this.tApprove = tApprove;
		this.pApprove = pApprove;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int gettApprove() {
		return tApprove;
	}

	public void settApprove(int tApprove) {
		this.tApprove = tApprove;
	}

	public int getpApprove() {
		return pApprove;
	}

	public void setpApprove(int pApprove) {
		this.pApprove = pApprove;
	}
	
	
	
	
	
}
