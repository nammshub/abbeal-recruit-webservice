package com.abbeal.recruitwebservice.services;

import java.util.Set;

import com.abbeal.recruitwebservice.entities.Answer;
import com.abbeal.recruitwebservice.entities.Question;

public interface AnswerService {

	void saveAll(Set<Answer> answers, Question question);

}
