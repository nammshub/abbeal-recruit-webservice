package com.abbeal.recruitwebservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.entities.Utilisateur;
import com.abbeal.recruitwebservice.repositories.UtilisateurRepository;

@Component
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	UtilisateurRepository userRepository;

	@Autowired
	QuizzService quizzService;

	@Autowired
	QuizzInstanceService quizzInstanceService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<Utilisateur> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<Utilisateur> find(String id) {
		return userRepository.findById(Long.parseLong(id));
	}

	@Override
	public Utilisateur save(Utilisateur user) {
		Utilisateur userCrypted = new Utilisateur(user.getFirstName(), user.getLastName(), user.getMail(),
				user.getPhoneNumber());
		userCrypted.setPassword(this.passwordEncoder.encode(user.getPassword()));
		userCrypted.setQuizz(user.getQuizz());
		userCrypted.setQuizzInstances(user.getQuizzInstances());
		return userRepository.save(userCrypted);

	}

	@Override
	public Optional<Utilisateur> findByMail(String candidateMail){
		return userRepository.findByMail(candidateMail);
	}

}
