package com.abbeal.recruitwebservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abbeal.recruitwebservice.Difficulty;
import com.abbeal.recruitwebservice.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	List<Question> findByField(String field);

	List<Question> findByFieldAndDifficulty(String field, Difficulty difficulty);

}
