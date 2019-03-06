package com.abbeal.recruitwebservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzContent;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.entities.User;
import com.abbeal.recruitwebservice.exceptions.QuizzNotPresentException;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;
import com.abbeal.recruitwebservice.repositories.QuizzRepository;

@Component
public class QuizzServiceImpl implements QuizzService {

	@Autowired
	QuizzRepository quizzRepository;

	@Autowired
	UserService userService;

	@Autowired
	QuizzContentService quizzContentService;

	@Autowired
	QuizzInstanceService quizzInstanceService;

	@Override
	public List<Quizz> findAllByCreator(User u) {
		List<Quizz> quizzList = quizzRepository.findByCreator(u);
		quizzList.parallelStream().forEach(q -> {
			if (q.getQuizzInstances() != null) {
				Set<QuizzInstance> quizzInstances = q.getQuizzInstances();
				q.setQuizzInstances(quizzInstances);
			}
		});
		return quizzList;
	}

	@Override
	public List<Quizz> findAll() {
		return quizzRepository.findAll();
	}

	@Override
	public List<Quizz> findAllByCreator(String id) throws UserNotPresentException {
		User u = userService.find(id);
		List<Quizz> allQuizz = quizzRepository.findByCreator(u);
		allQuizz.parallelStream().forEach(q -> {
			Set<QuizzContent> quizzContents = quizzContentService.findAllByQuizz(q);
			q.setQuizzContents(quizzContents);
			Set<QuizzInstance> quizzInstances = quizzInstanceService.findAllByQuizz(q);
			q.setQuizzInstances(quizzInstances);
		});
		return allQuizz;
	}

	@Override
	public Quizz save(Quizz quizz, String userId) throws UserNotPresentException {
		User creator = userService.find(userId);
		quizz.setCreator(creator);
		Quizz toSave = new Quizz(quizz.getName(), creator);
		Quizz resultQuizz = quizzRepository.save(toSave);
		quizzContentService.saveAll(new ArrayList<>(quizz.getQuizzContents()), resultQuizz);
		return toSave;
	}

	@Override
	public Quizz find(String id) throws QuizzNotPresentException {
		Optional<Quizz> optionnalQuizz = quizzRepository.findById(Long.parseLong(id));
		if (!optionnalQuizz.isPresent()) {
			throw new QuizzNotPresentException(id);
		}
		Quizz q = optionnalQuizz.get();
		Set<QuizzContent> quizzContents = quizzContentService.findAllByQuizz(q);
		q.setQuizzContents(quizzContents);
		Set<QuizzInstance> quizzInstances = quizzInstanceService.findAllByQuizz(q);
		q.setQuizzInstances(quizzInstances);
		return q;
	}

}
