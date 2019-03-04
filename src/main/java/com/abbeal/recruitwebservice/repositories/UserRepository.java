package com.abbeal.recruitwebservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abbeal.recruitwebservice.entities.User;

public interface UserRepository  extends JpaRepository<User, Long> {

}
