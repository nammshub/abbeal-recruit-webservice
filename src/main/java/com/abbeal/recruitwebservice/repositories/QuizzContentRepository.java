package com.abbeal.recruitwebservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzContent;

public interface QuizzContentRepository extends JpaRepository<QuizzContent, Long>{

	List<QuizzContent> findByQuizz(Quizz q);

}
