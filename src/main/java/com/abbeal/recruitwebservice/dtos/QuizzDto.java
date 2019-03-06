package com.abbeal.recruitwebservice.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class QuizzDto {

	private Long id;
	private String name;
	private LocalDateTime creationDate;
	private boolean isActive;
	private UserDto creator;
	private Set<QuizzContentDto> quizzContents;
	
}
