package com.abbeal.recruitwebservice.services;

import java.util.List;
import java.util.Set;

import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzContent;

public interface QuizzContentService {

	void saveAll(List<QuizzContent> quizzContents, Quizz resultQuizz);

	Set<QuizzContent> findAllByQuizz(Quizz q);

}
