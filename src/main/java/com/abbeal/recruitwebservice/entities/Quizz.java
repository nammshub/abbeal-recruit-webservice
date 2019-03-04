package com.abbeal.recruitwebservice.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Quizz {

	private @Id @GeneratedValue Long id;
	private String name;
	private LocalDateTime creationDate;
	private boolean isActive;
	@ManyToOne()
	@JsonIgnore
	private User creator;
	@OneToMany(mappedBy="quizz",cascade = CascadeType.ALL)
	private Set<QuizzInstance> quizzInstances;
	@OneToMany(mappedBy="quizz", cascade = CascadeType.ALL)
	private Set<QuizzContent> quizzContents;
	
	public Quizz(){}
	
	
	public Quizz(String name, User creator, LocalDateTime creationDate, boolean isActive, Set<QuizzContent> quizzContents) {
		super();
		this.name = name;
		this.creator = creator;
		this.creationDate = creationDate;
		this.isActive = isActive;
		this.quizzContents = quizzContents;
	}


	public Quizz(String name, User creator) {
		this.name = name;
		this.creator = creator;
		this.creationDate = LocalDateTime.now();
		this.isActive = true;
		this.quizzContents = new HashSet<>();
	}
	

	public Quizz(String name,User creator, Set<QuizzContent>quizzContents) {
		this.name = name;
		this.creator = creator;
		this.creationDate = LocalDateTime.now();
		this.isActive = true;
		this.quizzContents = quizzContents;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public boolean isActive() {
		return isActive;
	}
	
	public Set<QuizzContent> getQuizzContents() {
		return quizzContents;
	}

	public User getCreator() {
		return creator;
	}


	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public Set<QuizzInstance> getQuizzInstances() {
		return quizzInstances;
	}


	public void setQuizzInstances(Set<QuizzInstance> quizzInstances) {
		this.quizzInstances = quizzInstances;
	}


	public void setQuizzContents(Set<QuizzContent> quizzContents) {
		this.quizzContents = quizzContents;
	}

	
}