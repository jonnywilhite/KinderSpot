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
@Table(name="EDU_STUDENTS")
public class Student {
	
	@Id
	@Column(name="s_id")
	@SequenceGenerator(allocationSize=1, name="studentSequence", sequenceName="student_sequence")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="studentSequence")
	private int id;
	
	@Column(name="s_firstname")
	private String firstname;
	
	@Column(name="s_lastname")
	private String lastname;
	
	@Column(name="s_active")
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="t_id")
	private User teacher;
	
	@ManyToOne
	@JoinColumn(name="p_id")
	private User parent;
	
	public Student() {}

	public Student(int id, String firstname, String lastname, boolean active, User teacher, User parent) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.active = active;
		this.teacher = teacher;
		this.parent = parent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
