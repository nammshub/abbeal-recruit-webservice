package com.abbeal.recruitwebservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class ActualQuestion {
	
	private @Id @GeneratedValue Long id;
	@ManyToOne()
	@JsonIgnoreProperties("actualQuestions")  
	private QuizzInstance quizzInstance;
	@ManyToOne()
	@JsonIgnoreProperties("actualQuestions")  
	private Question question;
	@ManyToOne()
	@JsonIgnoreProperties("actualQuestions")  
	@Nullable
	private Answer answer = new Answer();
	
	
	
	public ActualQuestion() {
		super();
	}

	public ActualQuestion(QuizzInstance quizzInstance, Question question) {
		super();
		this.quizzInstance = quizzInstance;
		this.question = question;
	}


	public ActualQuestion(QuizzInstance quizzInstance, Question question, Answer answer) {
		super();
		this.quizzInstance = quizzInstance;
		this.question = question;
		this.answer = answer;
	}


	public Long getId() {
		return id;
	}


	public QuizzInstance getQuizzInstance() {
		return quizzInstance;
	}


	public void setQuizzInstance(QuizzInstance quizzInstance) {
		this.quizzInstance = quizzInstance;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	public Answer getAnswer() {
		return answer;
	}


	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	

}
