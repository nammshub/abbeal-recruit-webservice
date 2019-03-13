package com.abbeal.recruitwebservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.entities.Utilisateur;
import com.abbeal.recruitwebservice.exceptions.UserMailNotPresentException;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;
import com.abbeal.recruitwebservice.repositories.UtilisateurRepository;

@Component
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	UtilisateurRepository  userRepository;
	
	@Autowired
	QuizzService quizzService;
	
	@Autowired
	QuizzInstanceService quizzInstanceService;
	
	@Override
	public List<Utilisateur> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Utilisateur find(String id) throws UserNotPresentException {
		Optional<Utilisateur> user = userRepository.findById(Long.parseLong(id));
		if (!user.isPresent()) {
			throw new UserNotPresentException(id);
		}
		return user.get();
	}

	@Override
	public Utilisateur save(Utilisateur user) {
		return userRepository.save(user);
	}

	@Override
	public Utilisateur findByMail(String candidateMail) throws UserMailNotPresentException {
		Optional<Utilisateur> user = userRepository.findByMail(candidateMail);
		if (!user.isPresent()) {
			throw new UserMailNotPresentException(candidateMail);
		}
		return user.get();
	}
	
	
	
	

}
