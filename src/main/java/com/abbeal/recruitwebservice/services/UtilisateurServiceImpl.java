package com.abbeal.recruitwebservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.MyUserPrincipal;
import com.abbeal.recruitwebservice.entities.Utilisateur;
import com.abbeal.recruitwebservice.repositories.UtilisateurRepository;

@Component
public class UtilisateurServiceImpl implements UtilisateurService , UserDetailsService{

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
		return userRepository.save(userCrypted);

	}

	@Override
	public Optional<Utilisateur> findByMail(String candidateMail){
		return userRepository.findByMail(candidateMail);
	}

	@Override
	public UserDetails loadUserByUsername(String mail) {
		Optional<Utilisateur> user = userRepository.findByMail(mail);

	    if (!user.isPresent()) {
	        throw new UsernameNotFoundException(String.format("Le mail %s n'existe pas en base !", mail));
	    }

	    return new MyUserPrincipal(user.get());
	}

	@Override
	public Optional<Utilisateur> getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return this.findByMail(authentication.getName());
	}

}
