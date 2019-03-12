package com.abbeal.recruitwebservice.dtos;

import java.util.Set;

import lombok.Data;

@Data
public class QuestionResultDto {
		QuestionDto question;
		Set<AnswerDto> candidateAnswers;
		byte result;
}
