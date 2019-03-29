package com.abbeal.recruitwebservice.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.abbeal.recruitwebservice.dtos.QuestionResultDto;
import com.abbeal.recruitwebservice.dtos.QuizzSubmitDto;
import com.abbeal.recruitwebservice.entities.Candidate;
import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.entities.Utilisateur;
import com.abbeal.recruitwebservice.exceptions.AnswerNotPresentException;
import com.abbeal.recruitwebservice.exceptions.NotEnoughQuestionsException;
import com.abbeal.recruitwebservice.exceptions.QuestionNotPresentException;
import com.abbeal.recruitwebservice.exceptions.QuizzInstanceNotPresentException;
import com.abbeal.recruitwebservice.exceptions.QuizzNotPresentException;

public interface QuizzInstanceService {

	List<QuizzInstance> findAllByCandidate(Utilisateur u);

	Set<QuizzInstance> findAllByQuizz(Quizz q);
	
	QuizzInstance findById(long id) throws QuizzInstanceNotPresentException;

	QuizzInstance getNewInstance(String quizzId, Optional<String> candidateMail) throws QuizzNotPresentException, NotEnoughQuestionsException;

	QuizzInstance saveSubmitedQuizz(QuizzSubmitDto quizzSubmited) throws QuizzNotPresentException, QuestionNotPresentException, AnswerNotPresentException;
	
	QuizzInstance saveQuizzInstance(Quizz quizz, Candidate candidate);

	Set<QuestionResultDto> getQuizzInstanceResult(long quizzInstanceId) throws QuizzInstanceNotPresentException;

}
