package com.abbeal.recruitwebservice.services;

import java.util.Optional;

import com.abbeal.recruitwebservice.entities.Candidate;

public interface CandidateService {

	Optional<Candidate> findByMail(String mail);

	Candidate save(Candidate newCandidate);
}
