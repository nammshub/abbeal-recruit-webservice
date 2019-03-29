package com.abbeal.recruitwebservice.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.abbeal.recruitwebservice.Difficulty;
import lombok.Data;

@Entity
@Data
public class QuizzContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8983863249939856560L;
	private @Id @GeneratedValue Long id;
	@ManyToOne()
	private Quizz quizz;
	private String field;
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;
	private int number;
	
	public QuizzContent(Quizz quizz, String field, Difficulty difficulty, int number) {
		super();
		this.quizz = quizz;
		this.field = field;
		this.difficulty = difficulty;
		this.number = number;
	}

	public QuizzContent() {
		super();
	}
}
