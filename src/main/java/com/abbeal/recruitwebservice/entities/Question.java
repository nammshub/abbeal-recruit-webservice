package com.abbeal.recruitwebservice.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.abbeal.recruitwebservice.Difficulty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class Question {
	
	private @Id @GeneratedValue Long id;
	private String field;
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;
	private String statement;
	@OneToMany(mappedBy="question",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("question")
	private Set<Answer> answers;
	
	
	@OneToMany(mappedBy="question",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("question")
	private Set<ActualQuestion> actualQuestions;
	
	
	
	public Question() {
		super();
	}



	public Question(String field, Difficulty difficulty, String statement, Set<Answer> answers) {
		super();
		this.field = field;
		this.difficulty = difficulty;
		this.statement = statement;
		this.answers = answers;
	}



	public Question(Long id, String field, Difficulty difficulty, String statement) {
		super();
		this.id = id;
		this.field = field;
		this.difficulty = difficulty;
		this.statement = statement;
	}



	public Question(String field, Difficulty difficulty, String statement) {
		super();
		this.field = field;
		this.difficulty = difficulty;
		this.statement = statement;
	}



	public Long getId() {
		return id;
	}



	public String getField() {
		return field;
	}



	public void setField(String field) {
		this.field = field;
	}



	public Difficulty getDifficulty() {
		return difficulty;
	}



	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}



	public String getStatement() {
		return statement;
	}



	public void setStatement(String statement) {
		this.statement = statement;
	}



	public Set<Answer> getAnswers() {
		return answers;
	}



	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}



	public Set<ActualQuestion> getActualQuestions() {
		return actualQuestions;
	}



	public void setActualQuestions(Set<ActualQuestion> actualQuestions) {
		this.actualQuestions = actualQuestions;
	}


}
