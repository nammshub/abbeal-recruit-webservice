package com.abbeal.recruitwebservice.services;

import java.util.List;

import com.abbeal.recruitwebservice.entities.Utilisateur;
import com.abbeal.recruitwebservice.exceptions.UserMailNotPresentException;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;

public interface UtilisateurService {
	List<Utilisateur> findAll();

	Utilisateur find(String id) throws UserNotPresentException;

	Utilisateur save(Utilisateur user);

	Utilisateur findByMail(String candidateMail) throws UserMailNotPresentException;
}
