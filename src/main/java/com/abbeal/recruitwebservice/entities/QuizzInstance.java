package com.abbeal.recruitwebservice.entities;

import lombok.Data;

import java.time.LocalDateTime;
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

/**
 * 
 * @author dhain
 * 
 *         This class represents the object created when a candidate access the
 *         quizz => the quizz blue print is linked to this user id and the
 *         localdatetime of creation and a serie of questions is generated
 *         randomly (according to the quizz contents)
 *
 */
@Data
@Entity
public class QuizzInstance {

	private @Id @GeneratedValue Long id;
	@ManyToOne()
	@JsonIgnoreProperties("quizzInstances")
	private Quizz quizz;
	@ManyToOne()
	@JsonIgnoreProperties("quizzInstances")
	private User candidate;
	private LocalDateTime creationDate;
	@OneToMany(mappedBy="quizzInstance",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("quizzInstance")
	private Set<ActualQuestion> actualQuestions;
	@Transient
	private String quizzName;
	@Transient
	private String candidateMail;

	public QuizzInstance() {
		super();
		this.creationDate = LocalDateTime.now();
	}

	public QuizzInstance(Quizz quizz, User candidate) {
		super();
		this.quizz = quizz;
		this.candidate = candidate;
		this.creationDate = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public Quizz getQuizz() {
		return quizz;
	}

	public User getCandidate() {
		return candidate;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public String getQuizzName() {
		return quizzName;
	}

	public void ListQuizzName(String quizzName) {
		this.quizzName = quizzName;
	}

	public String getCandidateMail() {
		return candidateMail;
	}

	public void ListCandidateMail(String candidateMail) {
		this.candidateMail = candidateMail;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuizz(Quizz quizz) {
		this.quizz = quizz;
	}

	public void setCandidate(User candidate) {
		this.candidate = candidate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public void setQuizzName(String quizzName) {
		this.quizzName = quizzName;
	}

	public void setCandidateMail(String candidateMail) {
		this.candidateMail = candidateMail;
	}

	public Set<ActualQuestion> getActualQuestions() {
		return actualQuestions;
	}

	public void setActualQuestions(Set<ActualQuestion> actualQuestions) {
		this.actualQuestions = actualQuestions;
	}

	
	
}
