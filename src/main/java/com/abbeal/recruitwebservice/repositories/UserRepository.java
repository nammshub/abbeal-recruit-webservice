package com.abbeal.recruitwebservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abbeal.recruitwebservice.entities.User;

public interface UserRepository  extends JpaRepository<User, Long> {

	Optional<User> findByMail(String candidateMail);

}
