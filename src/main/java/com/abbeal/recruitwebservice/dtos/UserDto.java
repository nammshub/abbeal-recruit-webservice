package com.abbeal.recruitwebservice.dtos;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String mail;
	private String phoneNumber;

}
