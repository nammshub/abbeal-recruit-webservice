package com.abbeal.recruitwebservice.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abbeal.recruitwebservice.dtos.QuestionDto;
import com.abbeal.recruitwebservice.entities.Question;
import com.abbeal.recruitwebservice.services.QuestionService;

@RestController
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/questions")
	public Set<QuestionDto> findAllQuestion() {
		Set<Question> questions = questionService.findAll();
		return questions.stream().map(this::convertToDto)
        .collect(Collectors.toSet());
	}
	
	@GetMapping("/questions/{field}")
	public Set<QuestionDto> findAllQuestionByField(@PathVariable String field) {
		Set<Question> questions =questionService.findAllByField(field);
		return questions.stream().map(this::convertToDto)
		        .collect(Collectors.toSet());
	}
	
	@PostMapping("/questions")
	public ResponseEntity<HttpStatus> createQuestion(@RequestBody QuestionDto question) {
		questionService.save(convertToEntity(question));
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	private QuestionDto convertToDto(Question q) {
		return modelMapper.map(q, QuestionDto.class);
	}
	
	private Question convertToEntity(QuestionDto q) {
		return modelMapper.map(q, Question.class);
	}
}
