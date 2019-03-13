package com.abbeal.recruitwebservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abbeal.recruitwebservice.entities.Utilisateur;

public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Long> {

	Optional<Utilisateur> findByMail(String candidateMail);

}
