package com.abbeal.recruitwebservice.dtos;

import lombok.Data;

@Data
public class AnswerDto {

	private Long id;
	private String statement;
	private boolean isCorrect;

}
