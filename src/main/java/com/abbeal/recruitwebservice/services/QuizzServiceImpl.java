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
import com.abbeal.recruitwebservice.entities.Utilisateur;
import com.abbeal.recruitwebservice.exceptions.QuizzNotPresentException;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;
import com.abbeal.recruitwebservice.repositories.QuizzRepository;

@Component
public class QuizzServiceImpl implements QuizzService {

	@Autowired
	QuizzRepository quizzRepository;

	@Autowired
	UtilisateurService userService;

	@Autowired
	QuizzContentService quizzContentService;

	@Autowired
	QuizzInstanceService quizzInstanceService;

	@Override
	public List<Quizz> findAllByCreator(Utilisateur u) {
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
		Optional<Utilisateur> u = userService.find(id);
		List<Quizz> allQuizz = new ArrayList<>();
		if (u.isPresent()) {
			allQuizz = quizzRepository.findByCreator(u.get());
		}
		return allQuizz;
	}

	@Override
	public Quizz save(Quizz quizz, String userId) throws UserNotPresentException {
		Optional<Utilisateur> creator = userService.find(userId);
		Quizz toSave = null;
		if (creator.isPresent()) {
			quizz.setCreator(creator.get());
			toSave = new Quizz(quizz.getName(), creator.get());
			Quizz resultQuizz = quizzRepository.save(toSave);
			quizzContentService.saveAll(new ArrayList<>(quizz.getQuizzContents()), resultQuizz);
		}
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

	@Override
	public Quizz activate(String id, boolean state) throws QuizzNotPresentException {
		Optional<Quizz> optionnalQuizz = quizzRepository.findById(Long.parseLong(id));
		if (!optionnalQuizz.isPresent()) {
			throw new QuizzNotPresentException(id);
		}
		Quizz q = optionnalQuizz.get();
		q.setActive(state);
		return quizzRepository.save(q);
	}

}
