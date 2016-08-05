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
@Table(name="EDU_USERS")
public class User {
	
	@Id
	@Column(name="u_id")
	@SequenceGenerator(allocationSize=1, name="userSequence", sequenceName="user_sequence")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="userSequence")
	private int id;
	
	@Column(name="u_firstname")
	private String firstName;
	
	@Column(name="u_lastname")
	private String lastName;
	
	@Column(name="u_email")
	private String email;
	
	@Column(name="u_password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="u_role")
	private UserRole userRole;
	
	public User() {}

	public User(int id, String firstName, String lastName, String email, String password, UserRole userRole) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
}
