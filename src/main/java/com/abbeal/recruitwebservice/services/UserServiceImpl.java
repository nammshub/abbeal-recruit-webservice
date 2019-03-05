package com.abbeal.recruitwebservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.entities.User;
import com.abbeal.recruitwebservice.exceptions.UserMailNotPresentException;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;
import com.abbeal.recruitwebservice.repositories.UserRepository;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository  userRepository;
	
	@Autowired
	QuizzService quizzService;
	
	@Autowired
	QuizzInstanceService quizzInstanceService;
	
	@Override
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		users.parallelStream().forEach(u -> {
			List<Quizz> quizz = quizzService.findAllByCreator(u);
			u.ListQuizz(quizz);
			List<QuizzInstance> quizzInstances = quizzInstanceService.findAllByCandidate(u);
			u.ListQuizzInstances(quizzInstances);
		});
		return users;
	}

	@Override
	public User find(String id) throws UserNotPresentException {
		Optional<User> user = userRepository.findById(Long.parseLong(id));
		if (!user.isPresent()) {
			throw new UserNotPresentException(id);
		}
		User u = user.get();
		u.ListQuizz(quizzService.findAllByCreator(u));
		u.ListQuizzInstances(quizzInstanceService.findAllByCandidate(u));
		return u;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByMail(String candidateMail) throws UserMailNotPresentException {
		Optional<User> user = userRepository.findByMail(candidateMail);
		if (!user.isPresent()) {
			throw new UserMailNotPresentException(candidateMail);
		}
		User u = user.get();
		u.ListQuizz(quizzService.findAllByCreator(u));
		u.ListQuizzInstances(quizzInstanceService.findAllByCandidate(u));
		return u;
	}
	
	
	
	

}
