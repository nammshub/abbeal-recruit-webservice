package com.abbeal.recruitwebservice.services;

import java.util.Set;

import com.abbeal.recruitwebservice.entities.ActualQuestion;
import com.abbeal.recruitwebservice.entities.Answer;
import com.abbeal.recruitwebservice.entities.Question;
import com.abbeal.recruitwebservice.entities.QuizzInstance;

public interface ActualQuestionService {

	void save(QuizzInstance quizzInstance,Question question, Answer answer);

	void saveAll(Set<ActualQuestion> actualQuestions);
}
