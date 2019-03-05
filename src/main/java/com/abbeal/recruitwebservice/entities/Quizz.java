package com.abbeal.recruitwebservice.entities;

import lombok.Data;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
public class Quizz {

	private @Id @GeneratedValue Long id;
	private String name;
	private LocalDateTime creationDate;
	private boolean isActive;
	@ManyToOne()
	private User creator;
	@OneToMany(mappedBy="quizz",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("quizz")
	private Set<QuizzInstance> quizzInstances = new HashSet<>();
	@OneToMany(mappedBy="quizz", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("quizz")
	private Set<QuizzContent> quizzContents = new HashSet<>();
	@Transient
	private String creatorIdentity;
	
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


	public void ListCreator(User creator) {
		this.creator = creator;
	}
	
	public Set<QuizzInstance> getQuizzInstances() {
		return quizzInstances;
	}


	public void ListQuizzInstances(Set<QuizzInstance> quizzInstances) {
		this.quizzInstances = quizzInstances;
	}


	public void ListQuizzContents(Set<QuizzContent> quizzContents) {
		this.quizzContents = quizzContents;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public void setCreator(User creator) {
		this.creator = creator;
	}


	public void setQuizzInstances(Set<QuizzInstance> quizzInstances) {
		this.quizzInstances = quizzInstances;
	}


	public void setQuizzContents(Set<QuizzContent> quizzContents) {
		this.quizzContents = quizzContents;
	}


	public String getCreatorIdentity() {
		return creatorIdentity;
	}


	public void setCreatorIdentity(String creatorIdentity) {
		this.creatorIdentity = creatorIdentity;
	}

	
	
	
}