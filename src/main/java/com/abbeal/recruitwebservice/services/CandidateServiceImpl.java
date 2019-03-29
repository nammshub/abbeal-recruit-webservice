package com.abbeal.recruitwebservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abbeal.recruitwebservice.entities.Candidate;
import com.abbeal.recruitwebservice.repositories.CandidateRepository;

@Component
public class CandidateServiceImpl implements CandidateService {
	
	@Autowired
	CandidateRepository candidateRepository;

	@Override
	public Optional<Candidate> findByMail(String mail) {
		return candidateRepository.findByMail(mail);
	}

	@Override
	public Candidate save(Candidate newCandidate) {
		return candidateRepository.save(newCandidate);
	}

}
