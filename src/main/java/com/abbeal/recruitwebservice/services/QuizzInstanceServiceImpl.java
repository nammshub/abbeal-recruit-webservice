package com.abbeal.recruitwebservice.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.entities.ActualQuestion;
import com.abbeal.recruitwebservice.entities.Question;
import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.entities.User;
import com.abbeal.recruitwebservice.exceptions.NotEnoughQuestionsException;
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

	@Override
	public List<QuizzInstance> findAllByCandidate(User u) {
		List<QuizzInstance> quizzInstanceList = quizzInstanceRepository.findByCandidate(u);
		quizzInstanceList.parallelStream().forEach(q -> {
			q.setCandidateMail(u.getMail());
			q.setQuizzName(q.getQuizz().getName());
		});
		return quizzInstanceList;
	}

	@Override
	public Set<QuizzInstance> findAllByQuizz(Quizz q) {
		Set<QuizzInstance> quizzInstances = new HashSet<>(quizzInstanceRepository.findByQuizz(q));
		quizzInstances.parallelStream().forEach(i -> {
			i.ListCandidateMail(i.getCandidate().getMail());
			i.ListQuizzName(q.getName());
		});
		return quizzInstances;

	}

	@Override
	public QuizzInstance getNewInstance(String quizzId, String candidateMail)
			throws QuizzNotPresentException, NotEnoughQuestionsException {
		Quizz quizz = quizzService.find(quizzId);
		QuizzInstance quizzInstance = new QuizzInstance();
		quizzInstance.setQuizz(quizz);

		// generate random questions
		Set<Question> randomQuestions = questionService.findRandomQuestionsByQuizz(quizz);
		Set<ActualQuestion> actualQuestions = new HashSet<>();
		randomQuestions.parallelStream().forEach(q -> {
			ActualQuestion actualQuestion = new ActualQuestion(quizzInstance, q);
			actualQuestion.setQuestionStatement(q.getStatement());
			actualQuestion.setQuizzName(quizz.getName());
			actualQuestions.add(actualQuestion);
		});
		quizzInstance.setActualQuestions(actualQuestions);
		User candidate = new User();
		if (candidateMail != null) {
			quizzInstance.setCandidateMail(candidateMail);
			try {
				candidate = userService.findByMail(candidateMail);
			} catch (UserMailNotPresentException e) {
				log.warn(e.getMessage(), e);
				candidate.setMail(candidateMail);
			}
		}
		quizzInstance.setCandidate(candidate);
		quizzInstance.setQuizzName(quizz.getName());
		return quizzInstance;
	}

}
