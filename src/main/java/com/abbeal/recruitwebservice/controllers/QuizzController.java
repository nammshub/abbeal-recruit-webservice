package com.abbeal.recruitwebservice.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
import com.abbeal.recruitwebservice.entities.Utilisateur;
import com.abbeal.recruitwebservice.exceptions.QuizzNotPresentException;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;
import com.abbeal.recruitwebservice.services.QuizzService;
import com.abbeal.recruitwebservice.services.UtilisateurService;

@RestController
public class QuizzController {

	Logger log = LoggerFactory.getLogger(QuizzController.class);

	@Autowired
	private QuizzService quizzService;

	@Autowired
	UtilisateurService utilisateurService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/quizz")
	public List<QuizzDto> findAllQuizz() throws UserNotPresentException {
		Optional<Utilisateur> authenticatedUser = utilisateurService.getAuthenticatedUser();
		if (authenticatedUser.isPresent()) {
			List<Quizz> quizz = quizzService.findAllByCreator(authenticatedUser.get().getId().toString());
			return quizz.stream().map(this::convertToDto).collect(Collectors.toList());
		} else {
			return Collections.emptyList();
		}
	}

	// QUIZZ OF USER

	@GetMapping("/users/{id}/quizz")
	public List<QuizzDto> findAllUserQuizz(@PathVariable String id) throws UserNotPresentException {
		List<Quizz> quizz = quizzService.findAllByCreator(id);
		return quizz.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@PostMapping("/quizz")
	public ResponseEntity<QuizzDto> createQuizz(@RequestBody QuizzDto quizz) throws UserNotPresentException {
		Optional<Utilisateur> authenticatedUser = utilisateurService.getAuthenticatedUser();
		Quizz saved = null;
		if (authenticatedUser.isPresent()) {
			saved = quizzService.save(convertToEntity(quizz), authenticatedUser.get().getId().toString());
		}
		return ResponseEntity.status(HttpStatus.OK).body(convertToDto(saved));
	}

	@PatchMapping("quizz/{id}/activate")
	public ResponseEntity<HttpStatus> activateQuizz(@PathVariable String id) throws QuizzNotPresentException {
		quizzService.activate(id, true);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PatchMapping("quizz/{id}/deactivate")
	public ResponseEntity<HttpStatus> deactivateQuizz(@PathVariable String id) throws QuizzNotPresentException {
		quizzService.activate(id, false);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	private QuizzDto convertToDto(Quizz q) {
		return modelMapper.map(q, QuizzDto.class);
	}

	private Quizz convertToEntity(QuizzDto q) {
		return modelMapper.map(q, Quizz.class);
	}
}
