package com.abbeal.recruitwebservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzInstance;
import com.abbeal.recruitwebservice.entities.User;

public interface QuizzInstanceRepository extends JpaRepository<QuizzInstance, Long>{

	List<QuizzInstance> findByQuizz(Quizz quizz);

	List<QuizzInstance> findByCandidate(User u);

}
