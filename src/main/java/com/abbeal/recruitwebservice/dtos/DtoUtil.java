package com.abbeal.recruitwebservice.dtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.NullTolerantMapper;
import com.abbeal.recruitwebservice.entities.Answer;
import com.abbeal.recruitwebservice.entities.Question;

@Component
public class DtoUtil {
	
	@Autowired
	NullTolerantMapper modelMapper;

	public QuestionDto convertToDto(Question q) {
		return modelMapper.map(q, QuestionDto.class);
	}

	public Question convertToEntity(QuestionDto q) {
		return modelMapper.map(q, Question.class);
	}

	public AnswerDto convertToDto(Answer answer) {
		return modelMapper.map(answer, AnswerDto.class);
	}
}
