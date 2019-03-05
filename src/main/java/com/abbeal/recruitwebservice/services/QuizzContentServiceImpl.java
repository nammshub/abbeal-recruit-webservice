package com.abbeal.recruitwebservice.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.entities.Quizz;
import com.abbeal.recruitwebservice.entities.QuizzContent;
import com.abbeal.recruitwebservice.repositories.QuizzContentRepository;

@Component
public class QuizzContentServiceImpl implements QuizzContentService {
	
	@Autowired
	QuizzContentRepository quizzContentRepository;

	@Override
	public void saveAll(List<QuizzContent> quizzContents, Quizz resultQuizz) {
		quizzContents.stream().forEach(q -> q.ListQuizz(resultQuizz));
		quizzContentRepository.saveAll(quizzContents);
	}

	@Override
	public Set<QuizzContent> findAllByQuizz(Quizz q) {
		Set<QuizzContent> quizzContentList = new HashSet<QuizzContent>(quizzContentRepository.findByQuizz(q));
		return quizzContentList;
	}

	
}
