package com.abbeal.recruitwebservice.services;

import java.util.List;
import java.util.Optional;

import com.abbeal.recruitwebservice.entities.Utilisateur;

public interface UtilisateurService {
	List<Utilisateur> findAll();

	Optional<Utilisateur> find(String id);

	Utilisateur save(Utilisateur user);

	Optional<Utilisateur> findByMail(String candidateMail);
	
	Optional<Utilisateur> getAuthenticatedUser();
}
