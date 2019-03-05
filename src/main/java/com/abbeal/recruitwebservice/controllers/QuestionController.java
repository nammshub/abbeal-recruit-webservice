package com.abbeal.recruitwebservice.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abbeal.recruitwebservice.entities.Question;
import com.abbeal.recruitwebservice.services.QuestionService;

@RestController
public class QuestionController {
	
	@Autowired
	QuestionService questionService;

	@GetMapping("/questions")
	Set<Question> findAllQuestion() {
		return questionService.findAll();
	}
	
	@GetMapping("/questions/{field}")
	Set<Question> findAllQuestionByField(@PathVariable String field) {
		return questionService.findAllByField(field);
	}
	
	@PostMapping("/questions")
	ResponseEntity<HttpStatus> createUser(@RequestBody Question question) {
		questionService.save(question);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
