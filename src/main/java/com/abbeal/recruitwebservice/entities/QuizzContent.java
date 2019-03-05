package com.abbeal.recruitwebservice.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.abbeal.recruitwebservice.Difficulty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class QuizzContent {

	private @Id @GeneratedValue Long id;
	@ManyToOne()
	@JsonIgnoreProperties("quizzContents")
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
	
	public Long getId() {
		return id;
	}

	public Quizz getQuizz() {
		return quizz;
	}

	public String getField() {
		return field;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public int getNumber() {
		return number;
	}
	
	public void ListQuizz(Quizz quizz) {
		this.quizz = quizz;
	}

}
