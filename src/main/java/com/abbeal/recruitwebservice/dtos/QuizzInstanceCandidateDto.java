package com.abbeal.recruitwebservice.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class QuizzInstanceCandidateDto {

	private CandidateDto candidate;
	private LocalDateTime creationDate = LocalDateTime.now();
	private Set<ActualQuestionDto> actualQuestions;
	
}