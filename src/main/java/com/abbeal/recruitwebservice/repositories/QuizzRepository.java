package com.abbeal.recruitwebservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.Utilisateur;

public interface QuizzRepository extends JpaRepository<Quizz, Long> {

	List<Quizz> findByCreator(Utilisateur user);

}
