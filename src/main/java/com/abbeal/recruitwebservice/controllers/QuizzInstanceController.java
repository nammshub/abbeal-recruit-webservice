package com.abbeal.recruitwebservice.controllers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abbeal.recruitwebservice.dtos.QuizzInstanceCandidateDto;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.exceptions.NotEnoughQuestionsException;
import com.abbeal.recruitwebservice.exceptions.QuizzNotPresentException;
import com.abbeal.recruitwebservice.services.QuizzInstanceService;

@RestController
public class QuizzInstanceController {

	@Autowired
	QuizzInstanceService quizzInstanceService;
	
	@Autowired
	ModelMapper modelMapper;

	/**
	 * generate a new instance for this quizz and link to optional candidate mail
	 * 
	 * @return
	 * @throws QuizzNotPresentException
	 * @throws NotEnoughQuestionsException
	 */
	@GetMapping("/quizz-instances/{quizzId}")
	public QuizzInstanceCandidateDto getQuizzInstance(@RequestParam("mail") Optional<String> candidateMail, @PathVariable String quizzId)
			throws QuizzNotPresentException, NotEnoughQuestionsException {
		QuizzInstance quizzInstance = quizzInstanceService.getNewInstance(quizzId, candidateMail);
		return convertToDto(quizzInstance);
	}
	
	private QuizzInstanceCandidateDto convertToDto(QuizzInstance u) {
		return modelMapper.map(u, QuizzInstanceCandidateDto.class);
	}


}
