package com.abbeal.recruitwebservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
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
	private Answer answer = new Answer();
	@Transient
	private String quizzName;
	@Transient
	private String questionStatement;
	@Transient
	private String answerStatement;
	
	
	
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

	public String getQuizzName() {
		return quizzName;
	}

	public void setQuizzName(String quizzName) {
		this.quizzName = quizzName;
	}

	public String getQuestionStatement() {
		return questionStatement;
	}

	public void setQuestionStatement(String questionStatement) {
		this.questionStatement = questionStatement;
	}

	public String getAnswerStatement() {
		return answerStatement;
	}

	public void setAnswerStatement(String answerStatement) {
		this.answerStatement = answerStatement;
	}
	
	

}
