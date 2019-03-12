package com.abbeal.recruitwebservice.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.entities.ActualQuestion;
import com.abbeal.recruitwebservice.entities.Answer;
import com.abbeal.recruitwebservice.entities.Question;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.repositories.ActualQuestionRepository;

@Component
public class ActualQuestionServiceImpl implements ActualQuestionService {
	
	@Autowired
	ActualQuestionRepository actualQuestionRepository;

	@Override
	public void save(QuizzInstance quizzInstance, Question question, Answer answer) {
		ActualQuestion actual = new ActualQuestion(quizzInstance, question, answer);
		actualQuestionRepository.save(actual);

	}

	@Override
	public void saveAll(Set<ActualQuestion> actualQuestions) {
		actualQuestionRepository.saveAll(actualQuestions);
		
	}

}
