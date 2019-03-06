package com.abbeal.recruitwebservice.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.abbeal.recruitwebservice.Difficulty;
import lombok.Data;

@Data
@Entity
public class Question {
	
	private @Id @GeneratedValue Long id;
	private String field;
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;
	private String statement;
	@OneToMany(mappedBy="question",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Answer> answers;
	@OneToMany(mappedBy="question",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
}
