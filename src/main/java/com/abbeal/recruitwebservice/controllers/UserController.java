package com.abbeal.recruitwebservice.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzContent;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.entities.User;
import com.abbeal.recruitwebservice.exceptions.QuizzNotPresentException;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;
import com.abbeal.recruitwebservice.repositories.QuizzContentRepository;
import com.abbeal.recruitwebservice.repositories.QuizzInstanceRepository;
import com.abbeal.recruitwebservice.repositories.QuizzRepository;
import com.abbeal.recruitwebservice.repositories.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuizzRepository quizzRepository;

	@Autowired
	QuizzContentRepository quizzContentRepository;
	
	@Autowired
	QuizzInstanceRepository quizzInstanceRepository;

	@GetMapping("/users")
	List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	User findUser(@PathVariable String id) throws UserNotPresentException {
		Optional<User> user = userRepository.findById(Long.parseLong(id));
		if (!user.isPresent()) {
			throw new UserNotPresentException(id);
		}
		return user.get();
	}

	@PostMapping("/users")
	ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
		userRepository.save(user);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	// QUIZZ OF USER

	@GetMapping("/users/{id}/quizz")
	List<Quizz> findAllUserQuizz(@PathVariable String id) throws UserNotPresentException {
		Optional<User> user = userRepository.findById(Long.parseLong(id));
		if (!user.isPresent()) {
			throw new UserNotPresentException(id);
		}
		return quizzRepository.findByCreator(user.get());
	}

	@PostMapping("/users/{id}/quizz")
	ResponseEntity<HttpStatus> createQuizz(@PathVariable String id, @RequestBody Quizz quizz)
			throws UserNotPresentException {
		Optional<User> user = userRepository.findById(Long.parseLong(id));
		if (!user.isPresent()) {
			throw new UserNotPresentException(id);
		}

		Quizz toSave = new Quizz(quizz.getName(), user.get());
		Quizz resultQuizz = quizzRepository.save(toSave);
		Set<QuizzContent> quizzContents = quizz.getQuizzContents();
		quizzContents.stream().forEach(q -> q.setQuizz(resultQuizz));
		quizzContentRepository.saveAll(quizzContents);

		return ResponseEntity.ok(HttpStatus.OK);
	}

	// QUIZZ_INSTANCES OF QUIZZ OF USER
	@GetMapping("/users/{userId}/quizz/{quizzId}/instances")
	List<QuizzInstance> findAllUserQuizzInstances(@PathVariable String userId,@PathVariable String quizzId ) throws UserNotPresentException, QuizzNotPresentException {
		Optional<User> user = userRepository.findById(Long.parseLong(userId));
		if (!user.isPresent()) {
			throw new UserNotPresentException(userId);
		}
		Optional<Quizz> quizz = quizzRepository.findById(Long.parseLong(quizzId));
		if (!quizz.isPresent()) {
			throw new QuizzNotPresentException(quizzId);
		}
		return quizzInstanceRepository.findByQuizz(quizz.get());
	}

}
