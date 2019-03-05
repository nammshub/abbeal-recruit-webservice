package com.abbeal.recruitwebservice.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;
import com.abbeal.recruitwebservice.services.QuizzService;

@RestController
public class QuizzController {

	@Autowired
	private QuizzService quizzService;

	@GetMapping("/quizz")
	List<Quizz> findAllQuizz() {
		return quizzService.findAll();
	}

	// QUIZZ OF USER

	@GetMapping("/users/{id}/quizz")
	List<Quizz> findAllUserQuizz(@PathVariable String id) throws UserNotPresentException {
		return quizzService.findAllByCreator(id);
	}

	@PostMapping("/users/{id}/quizz")
	ResponseEntity<HttpStatus> createQuizz(@PathVariable String id, @RequestBody Quizz quizz)
			throws UserNotPresentException {
		quizzService.save(quizz, id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

}
