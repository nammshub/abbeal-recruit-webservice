package com.abbeal.recruitwebservice.dtos;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	@Email
	private String mail;
	private String phoneNumber;
	private String password;

}
