package com.abbeal.recruitwebservice.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abbeal.recruitwebservice.dtos.UserDto;
import com.abbeal.recruitwebservice.entities.Utilisateur;
import com.abbeal.recruitwebservice.exceptions.MailAlreadyPresentException;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;
import com.abbeal.recruitwebservice.services.UtilisateurService;

@RestController
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UtilisateurService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/users")
	public List<UserDto> findAllUsers() {
		final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("L'utilisateur courrant est {}", currentUserName);
		List<Utilisateur> users = userService.findAll();
		return users.parallelStream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> findUser(@PathVariable String id) throws UserNotPresentException {
		Optional<Utilisateur> optional = userService.find(id);
		if (optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(convertToDto(optional.get()));
		} else {
			throw new UserNotPresentException(id);
		}
	}

	@PostMapping("/users")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) throws MailAlreadyPresentException{
		Optional<Utilisateur> optional = userService.findByMail(user.getMail());
		if(!optional.isPresent()){
			UserDto savedUser =convertToDto(userService.save(convertToEntity(user)));
			return ResponseEntity.status(HttpStatus.OK).body(savedUser);
		}
		else {
			throw new MailAlreadyPresentException(user.getMail());
		}
	}

	private UserDto convertToDto(Utilisateur u) {
		return modelMapper.map(u, UserDto.class);
	}

	private Utilisateur convertToEntity(UserDto u) {
		return modelMapper.map(u, Utilisateur.class);
	}

}
