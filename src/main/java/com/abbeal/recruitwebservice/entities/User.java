package com.abbeal.recruitwebservice.entities;


import lombok.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
public class User {
	
	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	@Column(unique=true)
	private String mail;
	private String phoneNumber;
	@OneToMany(mappedBy="creator",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("creator")
	private List<Quizz> quizz;
	@OneToMany(mappedBy="candidate",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("candidate")
	private List<QuizzInstance> quizzInstances;
	
	
	
	public List<QuizzInstance> getQuizzInstances() {
		return quizzInstances;
	}


	public void ListQuizzInstances(List<QuizzInstance> quizzInstances) {
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


	public List<Quizz> getQuizz() {
		return quizz;
	}


	public void ListId(Long id) {
		this.id = id;
	}


	public void ListFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void ListLastName(String lastName) {
		this.lastName = lastName;
	}


	public void ListMail(String mail) {
		this.mail = mail;
	}


	public void ListPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public void ListQuizz(List<Quizz> quizz) {
		this.quizz = quizz;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public void setQuizz(List<Quizz> quizz) {
		this.quizz = quizz;
	}


	public void setQuizzInstances(List<QuizzInstance> quizzInstances) {
		this.quizzInstances = quizzInstances;
	}
	
	
	

}
