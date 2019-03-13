package com.abbeal.recruitwebservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abbeal.recruitwebservice.dtos.UserDto;
import com.abbeal.recruitwebservice.entities.Utilisateur;
import com.abbeal.recruitwebservice.exceptions.UserNotPresentException;
import com.abbeal.recruitwebservice.services.UtilisateurService;

@RestController
public class UserController {
	
	@Autowired
	UtilisateurService userService;
	
	@Autowired
    private ModelMapper modelMapper;

	
	@GetMapping("/users")
	public List<UserDto> findAllUsers() {
		List<Utilisateur> users = userService.findAll();
		return users.parallelStream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/users/{id}")
	public UserDto findUser(@PathVariable String id) throws UserNotPresentException {
		return convertToDto(userService.find(id));
	}

	@PostMapping("/users")
	public ResponseEntity<HttpStatus> createUser(@RequestBody UserDto user) {
		userService.save(convertToEntity(user));
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	private UserDto convertToDto(Utilisateur u) {
		return modelMapper.map(u, UserDto.class);
	}
	
	private Utilisateur convertToEntity(UserDto u) {
		return modelMapper.map(u, Utilisateur.class);
	}

}
