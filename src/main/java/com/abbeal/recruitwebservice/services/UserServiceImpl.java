package com.abbeal.recruitwebservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		return userRepository.findAll();
	}

	@Override
	public User find(String id) throws UserNotPresentException {
		Optional<User> user = userRepository.findById(Long.parseLong(id));
		if (!user.isPresent()) {
			throw new UserNotPresentException(id);
		}
		return user.get();
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
		return user.get();
	}
	
	
	
	

}
