package com.abbeal.recruitwebservice.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.Difficulty;
import com.abbeal.recruitwebservice.entities.Question;
import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzContent;
import com.abbeal.recruitwebservice.exceptions.NotEnoughQuestionsException;
import com.abbeal.recruitwebservice.repositories.QuestionRepository;

@Component
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerService answerService;

	@Override
	public Set<Question> findAll() {
		return new HashSet<>(questionRepository.findAll());
	}

	@Override
	public Set<Question> findAllByField(String field) {
		return new HashSet<>(questionRepository.findByField(field));
	}

	@Override
	public Question save(Question question) {
		Question saved = questionRepository.save(question);
		answerService.saveAll(question.getAnswers(), question);
		return saved;
	}

	@Override
	public Set<Question> findRandomQuestionsByQuizz(Quizz quizz) throws NotEnoughQuestionsException {
		Set<Question> questions = new HashSet<>();
		for(QuizzContent c : quizz.getQuizzContents()) {
			Set<Question> questionsPart;
			questionsPart = this.findRandomQuestionByFieldAndDifficulty(c.getField(),c.getDifficulty(),c.getNumber());
			questions.addAll(questionsPart);
		}
	
		return questions;
	}

	private Set<Question> findRandomQuestionByFieldAndDifficulty(String field, Difficulty difficulty, int number) throws NotEnoughQuestionsException {
		Set<Question> questions = new HashSet<>();
		List<Question> allQuestions = questionRepository.findByFieldAndDifficulty(field,difficulty);
		if(allQuestions.size() < number) {
			throw new NotEnoughQuestionsException(field,difficulty,number);
		}
	    Collections.shuffle(allQuestions);
	    questions.addAll(allQuestions.subList(0, number));
		return questions;
	}

	
	
}
