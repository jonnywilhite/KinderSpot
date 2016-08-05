package com.ex.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
}
