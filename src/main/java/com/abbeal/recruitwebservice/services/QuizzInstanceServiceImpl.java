package com.abbeal.recruitwebservice.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.dtos.AnswerDto;
import com.abbeal.recruitwebservice.dtos.QuestionDto;
import com.abbeal.recruitwebservice.dtos.QuizzSubmitDto;
import com.abbeal.recruitwebservice.entities.ActualQuestion;
import com.abbeal.recruitwebservice.entities.Answer;
import com.abbeal.recruitwebservice.entities.Question;
import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.entities.User;
import com.abbeal.recruitwebservice.exceptions.AnswerNotPresentException;
import com.abbeal.recruitwebservice.exceptions.NotEnoughQuestionsException;
import com.abbeal.recruitwebservice.exceptions.QuestionNotPresentException;
import com.abbeal.recruitwebservice.exceptions.QuizzNotPresentException;
import com.abbeal.recruitwebservice.exceptions.UserMailNotPresentException;
import com.abbeal.recruitwebservice.repositories.QuizzInstanceRepository;

@Component
public class QuizzInstanceServiceImpl implements QuizzInstanceService {

	Logger log = LoggerFactory.getLogger(QuizzInstanceServiceImpl.class);

	@Autowired
	QuizzInstanceRepository quizzInstanceRepository;

	@Autowired
	QuizzService quizzService;

	@Autowired
	QuestionService questionService;

	@Autowired
	UserService userService;

	@Autowired
	ActualQuestionService actualQuestionService;

	@Autowired
	AnswerService answerService;

	@Override
	public List<QuizzInstance> findAllByCandidate(User u) {
		return quizzInstanceRepository.findByCandidate(u);
	}

	@Override
	public Set<QuizzInstance> findAllByQuizz(Quizz q) {
		return new HashSet<>(quizzInstanceRepository.findByQuizz(q));
	}

	@Override
	public QuizzInstance getNewInstance(String quizzId, Optional<String> candidateMail)
			throws QuizzNotPresentException, NotEnoughQuestionsException {
		Quizz quizz = quizzService.find(quizzId);
		QuizzInstance quizzInstance = new QuizzInstance();
		quizzInstance.setActualQuestions(getRandomQuestions(quizz, quizzInstance));
		quizzInstance.setCandidate(prepareCandidate(candidateMail));
		return quizzInstance;
	}

	private User prepareCandidate(Optional<String> candidateMail) {
		User candidate = new User();
		if (candidateMail.isPresent()) {
			try {
				candidate = userService.findByMail(candidateMail.get());
			} catch (UserMailNotPresentException e) {
				log.warn(e.getMessage(), e);
				candidate.setMail(candidateMail.get());
			}
		}
		return candidate;
	}

	private Set<ActualQuestion> getRandomQuestions(Quizz quizz, QuizzInstance quizzInstance)
			throws NotEnoughQuestionsException {
		// generate random questions
		Set<Question> randomQuestions = questionService.findRandomQuestionsByQuizz(quizz);
		Set<ActualQuestion> actualQuestions = new HashSet<>();
		randomQuestions.parallelStream().forEach(q -> {
			ActualQuestion actualQuestion = new ActualQuestion(quizzInstance, q);
			actualQuestions.add(actualQuestion);
		});
		return actualQuestions;
	}

	@Override
	public QuizzInstance saveSubmitedQuizz(QuizzSubmitDto quizzSubmited) throws QuizzNotPresentException, QuestionNotPresentException, AnswerNotPresentException {
		Quizz quizz = quizzService.find(Long.toString(quizzSubmited.getQuizzId()));
		User candidate;
		try {
			candidate = userService.findByMail(quizzSubmited.getCandidateMail());
		} catch (UserMailNotPresentException e) {
			User newUser = new User(quizzSubmited.getCandidateMail());
			candidate = userService.save(newUser);
		}
		Set<ActualQuestion> actualQuestions = new HashSet<>();
		QuizzInstance quizzInstance = saveQuizzInstance(quizz, candidate);
		for (QuestionDto question : quizzSubmited.getQuestions()) {
			Optional<Question> q = questionService.find(question.getId());
			if (!q.isPresent()) {
				throw new QuestionNotPresentException(Long.toString(question.getId()));
			}
			for (AnswerDto answer : question.getAnswers()) {
				Optional<Answer> a = answerService.find(answer.getId());
				if (!a.isPresent()) {
					throw new AnswerNotPresentException(Long.toString(answer.getId()));
				}
				actualQuestions.add(new ActualQuestion(quizzInstance, q.get(), a.get()));
			}
		}
		
		actualQuestionService.saveAll(actualQuestions);
		return quizzInstance;
	}

	@Override
	public QuizzInstance saveQuizzInstance(Quizz quizz, User candidate) {
		QuizzInstance quizzInstance = new QuizzInstance(quizz, candidate);
		return quizzInstanceRepository.save(quizzInstance);
	}

}
