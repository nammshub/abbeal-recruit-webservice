package com.abbeal.recruitwebservice.services;

import java.util.List;
import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.User;
import com.abbeal.recruitwebservice.exceptions.QuizzNotPresentException;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;

public interface QuizzService {
	List<Quizz> findAllByCreator(User u);

	List<Quizz> findAll();
	
	Quizz find(String id) throws QuizzNotPresentException;

	List<Quizz> findAllByCreator(String id) throws UserNotPresentException;

	Quizz save(Quizz quizz, String userId) throws UserNotPresentException;
}
