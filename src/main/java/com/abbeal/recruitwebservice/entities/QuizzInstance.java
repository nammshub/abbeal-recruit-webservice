package com.abbeal.recruitwebservice.entities;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class QuizzInstance  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8840076909902397693L;
	private @Id @GeneratedValue Long id;
	@ManyToOne()
	private Quizz quizz;
	@ManyToOne()
	private Candidate candidate;
	private LocalDateTime creationDate;
	@OneToMany(mappedBy="quizzInstance",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<ActualQuestion> actualQuestions;

	public QuizzInstance() {
		super();
		this.creationDate = LocalDateTime.now();
	}

	public QuizzInstance(Quizz quizz, Candidate candidate) {
		super();
		this.quizz = quizz;
		this.candidate = candidate;
		this.creationDate = LocalDateTime.now();
	}
}
