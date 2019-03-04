package com.abbeal.recruitwebservice.entities;


import lombok.Data;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Data
@Entity
public class User {
	
	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	@Column(unique=true)
	private String mail;
	private String phoneNumber;
	@OneToMany(mappedBy="creator",cascade = CascadeType.ALL)
	Set<Quizz> quizz;
	@OneToMany(mappedBy="candidate",cascade = CascadeType.ALL)
	Set<QuizzInstance> quizzInstances;
	
	
	
	public Set<QuizzInstance> getQuizzInstances() {
		return quizzInstances;
	}


	public void setQuizzInstances(Set<QuizzInstance> quizzInstances) {
		this.quizzInstances = quizzInstances;
	}


	public User() {
		super();
	}


	public User(String mail) {
		super();
		this.mail = mail;
	}


	public User(String firstName, String lastName, String mail, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
	}


	public Long getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getMail() {
		return mail;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public Set<Quizz> getQuizz() {
		return quizz;
	}
	
	

}
