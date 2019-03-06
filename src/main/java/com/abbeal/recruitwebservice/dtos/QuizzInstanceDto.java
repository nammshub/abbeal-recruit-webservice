package com.abbeal.recruitwebservice.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class QuizzInstanceDto {

	
	private Long id;
	private UserDto candidate;
	private LocalDateTime creationDate;
}
