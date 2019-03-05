package com.abbeal.recruitwebservice.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.exceptions.NotEnoughQuestionsException;
import com.abbeal.recruitwebservice.exceptions.QuizzNotPresentException;
import com.abbeal.recruitwebservice.services.QuizzInstanceService;

@RestController
public class QuizzInstanceController {

	@Autowired
	QuizzInstanceService quizzInstanceService;

	/**
	 * generate a new instance for this quizz and link to optionnal candidate mail
	 * 
	 * @return
	 * @throws QuizzNotPresentException
	 * @throws NotEnoughQuestionsException
	 */
	@GetMapping("/quizz-instance/{quizzId}")
	QuizzInstance getQuizzInstance(@RequestParam("mail") Optional<String> candidateMail, @PathVariable String quizzId)
			throws QuizzNotPresentException, NotEnoughQuestionsException {
		QuizzInstance quizzInstance;
		if (candidateMail.isPresent()) {
			quizzInstance = quizzInstanceService.getNewInstance(quizzId, candidateMail.get());
		} else {
			quizzInstance = quizzInstanceService.getNewInstance(quizzId, null);
		}
		return quizzInstance;
	}

}
