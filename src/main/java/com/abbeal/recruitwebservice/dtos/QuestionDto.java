package com.abbeal.recruitwebservice.dtos;

import java.util.Set;

import com.abbeal.recruitwebservice.Difficulty;
import lombok.Data;

@Data
public class QuestionDto {

	private Long id;
	private String field;
	private Difficulty difficulty;
	private String statement;
	private Set<AnswerDto> answers;
	
}
