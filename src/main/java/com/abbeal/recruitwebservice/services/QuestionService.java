package com.abbeal.recruitwebservice.services;

import java.util.Set;

import com.abbeal.recruitwebservice.entities.Question;
import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.exceptions.NotEnoughQuestionsException;

public interface QuestionService {

	Set<Question> findAll();

	Set<Question> findAllByField(String field);

	Question save(Question question);

	Set<Question> findRandomQuestionsByQuizz(Quizz quizz) throws NotEnoughQuestionsException;

}
