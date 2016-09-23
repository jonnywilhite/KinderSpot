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
@Table(name ="EDU_MEETINGS")
public class Meetings {
	@Id
	@Column(name ="M_ID")
	@GeneratedValue
//	@SequenceGenerator(allocationSize = 1, name ="meetingSeq", sequenceName ="MEETING_SEQUENCE")
//	@GeneratedValue (strategy=GenerationType.SEQUENCE, generator ="meetingSeq")
	private int id;
	
	@Column(name ="M_DATE")
	private Timestamp date;
	
	@Column(name ="M_REASON")
	private String reason;
	
	@Column(name ="M_T_APPROVED")
	private boolean tApprove;
	
	@Column(name ="M_P_APPROVED")
	private boolean pApprove;
	
	@ManyToOne
	@JoinColumn(name="t_id")
	private User teacher;
	
	@ManyToOne
	@JoinColumn(name="p_id")
	private User parent;
	
	public Meetings (){}

	public Meetings(int id, Timestamp date, String reason, boolean tApprove, boolean pApprove, User teacher,
			User parent) {
		super();
		this.id = id;
		this.date = date;
		this.reason = reason;
		this.tApprove = tApprove;
		this.pApprove = pApprove;
		this.teacher = teacher;
		this.parent = parent;
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

	public boolean istApprove() {
		return tApprove;
	}

	public void settApprove(boolean tApprove) {
		this.tApprove = tApprove;
	}

	public boolean ispApprove() {
		return pApprove;
	}

	public void setpApprove(boolean pApprove) {
		this.pApprove = pApprove;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}
	
}
