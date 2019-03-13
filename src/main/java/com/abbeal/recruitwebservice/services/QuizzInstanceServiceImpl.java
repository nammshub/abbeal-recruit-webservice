package com.abbeal.recruitwebservice.services;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.dtos.AnswerDto;
import com.abbeal.recruitwebservice.dtos.DtoUtil;
import com.abbeal.recruitwebservice.dtos.QuestionDto;
import com.abbeal.recruitwebservice.dtos.QuestionResultDto;
import com.abbeal.recruitwebservice.dtos.QuizzSubmitDto;
import com.abbeal.recruitwebservice.entities.ActualQuestion;
import com.abbeal.recruitwebservice.entities.Answer;
import com.abbeal.recruitwebservice.entities.Question;
import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.entities.Utilisateur;
import com.abbeal.recruitwebservice.exceptions.AnswerNotPresentException;
import com.abbeal.recruitwebservice.exceptions.NotEnoughQuestionsException;
import com.abbeal.recruitwebservice.exceptions.QuestionNotPresentException;
import com.abbeal.recruitwebservice.exceptions.QuizzInstanceNotPresentException;
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
	UtilisateurService userService;

	@Autowired
	ActualQuestionService actualQuestionService;

	@Autowired
	AnswerService answerService;

	@Autowired
	DtoUtil dtoUtil;

	@Override
	public List<QuizzInstance> findAllByCandidate(Utilisateur u) {
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

	private Utilisateur prepareCandidate(Optional<String> candidateMail) {
		Utilisateur candidate = new Utilisateur();
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
	public QuizzInstance saveSubmitedQuizz(QuizzSubmitDto quizzSubmited)
			throws QuizzNotPresentException, QuestionNotPresentException, AnswerNotPresentException {
		Quizz quizz = quizzService.find(Long.toString(quizzSubmited.getQuizzId()));
		Utilisateur candidate;
		try {
			candidate = userService.findByMail(quizzSubmited.getCandidateMail());
		} catch (UserMailNotPresentException e) {
			Utilisateur newUser = new Utilisateur(quizzSubmited.getCandidateMail());
			candidate = userService.save(newUser);
		}
		Set<ActualQuestion> actualQuestions = new HashSet<>();
		QuizzInstance quizzInstance = saveQuizzInstance(quizz, candidate);
		for (QuestionDto question : quizzSubmited.getQuestions()) {
			Optional<Question> q = questionService.find(question.getId());
			if (!q.isPresent()) {
				throw new QuestionNotPresentException(Long.toString(question.getId()));
			}
			if (question.getAnswers().isEmpty()) {
				actualQuestions.add(new ActualQuestion(quizzInstance, q.get(), null));
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
	public QuizzInstance saveQuizzInstance(Quizz quizz, Utilisateur candidate) {
		QuizzInstance quizzInstance = new QuizzInstance(quizz, candidate);
		return quizzInstanceRepository.save(quizzInstance);
	}

	@Override
	public Set<QuestionResultDto> getQuizzInstanceResult(long quizzInstanceId) throws QuizzInstanceNotPresentException {
		QuizzInstance quizzInstance = this.findById(quizzInstanceId);
		Set<QuestionResultDto> questionResults = new HashSet<>();
		Map<Question, List<ActualQuestion>> actualsPerQuestion = quizzInstance.getActualQuestions().stream()
				.collect(Collectors.groupingBy(ActualQuestion::getQuestion));
		actualsPerQuestion.forEach((question, candidateAnswers) -> {
			QuestionResultDto questionResult = new QuestionResultDto();
			questionResult.setQuestion(dtoUtil.convertToDto(question));
			questionResult.setCandidateAnswers(candidateAnswers.stream()
					.map(actual -> dtoUtil.convertToDto(actual.getAnswer())).collect(Collectors.toSet()));
			questionResult.setResult(questionResult.getQuestion().getAnswers().stream()
					.filter(AnswerDto::isCorrect).count() == questionResult.getCandidateAnswers().stream()
							.filter(AnswerDto::isCorrect).count() ? Byte.parseByte("1") : Byte.parseByte("0"));
			questionResults.add(questionResult);
		});

		return questionResults;
	}

	@Override
	public QuizzInstance findById(long id) throws QuizzInstanceNotPresentException {
		Optional<QuizzInstance> optional = quizzInstanceRepository.findById(id);
		if (!optional.isPresent()) {
			throw new QuizzInstanceNotPresentException(id);
		}
		return optional.get();
	}

}
