package com.abbeal.recruitwebservice.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.repositories.QuizzRepository;

@RestController
public class QuizzController {
	
	@Autowired
	private QuizzRepository quizzRepository;
	
	@GetMapping("/quizz")
	List<Quizz> findAllQuizz() {
		return quizzRepository.findAll();
	}



}
