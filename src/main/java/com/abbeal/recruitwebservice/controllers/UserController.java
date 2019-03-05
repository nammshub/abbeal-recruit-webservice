package com.abbeal.recruitwebservice.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abbeal.recruitwebservice.entities.User;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;
import com.abbeal.recruitwebservice.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	
	@GetMapping("/users")
	List<User> findAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	User findUser(@PathVariable String id) throws UserNotPresentException {
		return userService.find(id);
	}

	@PostMapping("/users")
	ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
		userService.save(user);
		return ResponseEntity.ok(HttpStatus.OK);
	}

}
