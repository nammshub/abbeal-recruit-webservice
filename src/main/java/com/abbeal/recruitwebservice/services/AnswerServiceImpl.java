package com.abbeal.recruitwebservice.services;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.entities.Answer;
import com.abbeal.recruitwebservice.entities.Question;
import com.abbeal.recruitwebservice.repositories.AnswerRepository;

@Component
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	AnswerRepository answerRepository;

	@Override
	public void saveAll(Set<Answer> answers, Question question) {
		answers.stream().forEach(a -> a.setQuestion(question));
		answerRepository.saveAll(new ArrayList<>(answers));
	}

}
