package com.abbeal.recruitwebservice.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.entities.User;
import com.abbeal.recruitwebservice.exceptions.NotEnoughQuestionsException;
import com.abbeal.recruitwebservice.exceptions.QuizzNotPresentException;

public interface QuizzInstanceService {

	List<QuizzInstance> findAllByCandidate(User u);

	Set<QuizzInstance> findAllByQuizz(Quizz q);

	QuizzInstance getNewInstance(String quizzId, Optional<String> candidateMail) throws QuizzNotPresentException, NotEnoughQuestionsException;

}
