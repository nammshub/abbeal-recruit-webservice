package com.abbeal.recruitwebservice.dtos;

import com.abbeal.recruitwebservice.Difficulty;
import lombok.Data;

@Data
public class QuizzContentDto {

	private Long id;
	private String field;
	private Difficulty difficulty;
	private int number;
}
