package com.abbeal.recruitwebservice.services;

import java.util.List;

import com.abbeal.recruitwebservice.entities.User;
import com.abbeal.recruitwebservice.exceptions.UserMailNotPresentException;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;

public interface UserService {
	List<User> findAll();

	User find(String id) throws UserNotPresentException;

	User save(User user);

	User findByMail(String candidateMail) throws UserMailNotPresentException;
}
