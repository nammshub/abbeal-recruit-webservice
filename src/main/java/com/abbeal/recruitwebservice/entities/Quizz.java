package com.abbeal.recruitwebservice.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Getter
@Setter
@Entity
public class Quizz {

	private @Id @GeneratedValue Long id;
	private String name;
	private LocalDateTime creationDate;
	private boolean isActive;
	@ManyToOne()
	private User creator;
	@OneToMany(mappedBy="quizz",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<QuizzInstance> quizzInstances = new HashSet<>();
	@OneToMany(mappedBy="quizz", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<QuizzContent> quizzContents = new HashSet<>();
	
	
	
	public Quizz(){
	}
	
	
	public Quizz(String name, User creator, LocalDateTime creationDate, boolean isActive, Set<QuizzContent> quizzContents) {
		super();
		this.name = name;
		this.creator = creator;
		this.creationDate = creationDate;
		this.isActive = isActive;
		this.quizzContents = quizzContents;
	}


	public Quizz(String name, User creator) {
		super();
		this.name = name;
		this.creator = creator;
		this.creationDate = LocalDateTime.now();
		this.isActive = true;
	}
	

	public Quizz(String name,User creator, Set<QuizzContent>quizzContents) {
		this.name = name;
		this.creator = creator;
		this.creationDate = LocalDateTime.now();
		this.isActive = true;
		this.quizzContents = quizzContents;
	}
}