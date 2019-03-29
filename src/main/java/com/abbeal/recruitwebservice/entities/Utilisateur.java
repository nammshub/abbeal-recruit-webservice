package com.abbeal.recruitwebservice.entities;


import lombok.Data;

import java.io.Serializable;
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
public class Utilisateur implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2275012728499046724L;
	
	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	@Column(unique=true)
	private String mail;
	private String phoneNumber;
	@OneToMany(mappedBy="creator",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("creator")
	private List<Quizz> quizz;
	private String password;
	
	
	
	

	public Utilisateur() {
		super();
	}


	public Utilisateur(String mail) {
		super();
		this.mail = mail;
	}


	public Utilisateur(String firstName, String lastName, String mail, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
	}

}
