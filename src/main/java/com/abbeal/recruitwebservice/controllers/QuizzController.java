package com.abbeal.recruitwebservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abbeal.recruitwebservice.dtos.QuizzDto;
import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.exceptions.QuizzNotPresentException;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;
import com.abbeal.recruitwebservice.services.QuizzService;

@RestController
public class QuizzController {
	
	Logger log = LoggerFactory.getLogger(QuizzController.class);

	@Autowired
	private QuizzService quizzService;
	

    @Autowired
    private ModelMapper modelMapper;

	@GetMapping("/quizz")
	public List<QuizzDto> findAllQuizz() {
		List<Quizz> quizz =  quizzService.findAll();
		return quizz.stream()
		          .map(this::convertToDto)
		          .collect(Collectors.toList());
	}

	// QUIZZ OF USER

	@GetMapping("/users/{id}/quizz")
	public List<QuizzDto> findAllUserQuizz(@PathVariable String id) throws UserNotPresentException {
		List<Quizz> quizz =quizzService.findAllByCreator(id);
		return quizz.stream()
		          .map(this::convertToDto)
		          .collect(Collectors.toList());
	}

	@PostMapping("/users/{id}/quizz")
	public ResponseEntity<HttpStatus> createQuizz(@PathVariable String id, @RequestBody QuizzDto quizz)
			throws UserNotPresentException {
		quizzService.save(convertToEntity(quizz), id);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PatchMapping("quizz/{id}/activate")
	public ResponseEntity<HttpStatus> activateQuizz(@PathVariable String id)
			throws QuizzNotPresentException {
		quizzService.activate(id,true);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PatchMapping("quizz/{id}/deactivate")
	public ResponseEntity<HttpStatus> deactivateQuizz(@PathVariable String id)
			throws QuizzNotPresentException {
		quizzService.activate(id,false);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	private QuizzDto convertToDto(Quizz q) {
		return modelMapper.map(q, QuizzDto.class);
	}
	
	private Quizz convertToEntity(QuizzDto q) {
		return modelMapper.map(q, Quizz.class);
	}
}
