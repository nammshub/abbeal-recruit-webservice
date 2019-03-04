package com.abbeal.recruitwebservice.entities;

import lombok.Data;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private Quizz quizz;
	@ManyToOne()
	@JsonIgnore
	private User candidate;
	private LocalDateTime creationDate;

	public QuizzInstance() {
		super();
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

}
