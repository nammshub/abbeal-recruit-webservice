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
public class Candidate implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7459685001078311825L;
	@Id @GeneratedValue
	private Long id;
	@Column(unique=true)
	private String mail;
	@OneToMany(mappedBy="candidate",cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
	@JsonIgnoreProperties("candidate")
	private List<QuizzInstance> quizzInstances;
	
	public Candidate(String candidateMail) {
		this.mail = candidateMail;
	}

	public Candidate() {
	}

}
