package com.abbeal.recruitwebservice.dtos;

import java.util.Set;

import lombok.Data;

@Data
public class QuizzSubmitDto {

	 private long quizzId;
     private String candidateMail;
     private Set<QuestionDto>questions;
}
