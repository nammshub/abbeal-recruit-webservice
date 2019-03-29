package com.abbeal.recruitwebservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abbeal.recruitwebservice.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate,Long>{

	Optional<Candidate> findByMail(String mail);
}
