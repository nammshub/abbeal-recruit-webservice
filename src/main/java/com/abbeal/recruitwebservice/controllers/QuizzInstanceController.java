package com.abbeal.recruitwebservice.controllers;

import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abbeal.recruitwebservice.dtos.QuestionResultDto;
import com.abbeal.recruitwebservice.dtos.QuizzInstanceCandidateDto;
import com.abbeal.recruitwebservice.dtos.QuizzInstanceDto;
import com.abbeal.recruitwebservice.dtos.QuizzSubmitDto;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.exceptions.AnswerNotPresentException;
import com.abbeal.recruitwebservice.exceptions.NotEnoughQuestionsException;
import com.abbeal.recruitwebservice.exceptions.QuestionNotPresentException;
import com.abbeal.recruitwebservice.exceptions.QuizzInstanceNotPresentException;
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
	public QuizzInstanceCandidateDto getQuizzInstance(@RequestParam("mail") Optional<String> candidateMail,
			@PathVariable String quizzId) throws QuizzNotPresentException, NotEnoughQuestionsException {
		QuizzInstance quizzInstance = quizzInstanceService.getNewInstance(quizzId, candidateMail);
		return convertToDto(quizzInstance);
	}

	@PostMapping("/quizz-instances")
	public ResponseEntity<QuizzInstanceDto> submitQuizz(@RequestBody QuizzSubmitDto quizzSubmited)
			throws QuizzNotPresentException, QuestionNotPresentException, AnswerNotPresentException {
		QuizzInstanceDto savezQuizzInstance = convertToIdDto(
				quizzInstanceService.saveSubmitedQuizz(quizzSubmited));
		return ResponseEntity.status(HttpStatus.OK).body(savezQuizzInstance);
	}
	
	@GetMapping("/quizz-instances/results/{quizzInstanceId}")
	public ResponseEntity<Set<QuestionResultDto>> getQuizzInstanceResult(@PathVariable long quizzInstanceId) throws QuizzInstanceNotPresentException{
		Set<QuestionResultDto> questionResults = quizzInstanceService.getQuizzInstanceResult(quizzInstanceId);
		return ResponseEntity.status(HttpStatus.OK).body(questionResults);
	}

	private QuizzInstanceCandidateDto convertToDto(QuizzInstance u) {
		return modelMapper.map(u, QuizzInstanceCandidateDto.class);
	}
	
	private QuizzInstanceDto convertToIdDto(QuizzInstance u) {
		return modelMapper.map(u, QuizzInstanceDto.class);
	}

}
