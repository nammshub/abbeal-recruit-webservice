package com.abbeal.recruitwebservice.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4262452121204249445L;
	private @Id @GeneratedValue Long id;
	private String statement;
	private boolean isCorrect;
	@ManyToOne()
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
}
