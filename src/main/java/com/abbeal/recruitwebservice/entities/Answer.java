package com.abbeal.recruitwebservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Answer {
	
	private @Id @GeneratedValue Long id;
	private String statement;
	private boolean isCorrect;
	@ManyToOne()
	@JsonIgnoreProperties("answers")
	private Question question;
	
	
	public Answer() {
		super();
	}
	
	public Answer(String statement, boolean isCorrect, Question question) {
		super();
		this.statement = statement;
		this.isCorrect = isCorrect;
		this.question = question;
	}

	public Long getId() {
		return id;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
}
