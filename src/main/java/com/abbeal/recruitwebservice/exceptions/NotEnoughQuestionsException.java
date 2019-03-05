package com.abbeal.recruitwebservice.exceptions;

import com.abbeal.recruitwebservice.Difficulty;

public class NotEnoughQuestionsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6682304025101931900L;

	public NotEnoughQuestionsException(String field, Difficulty difficulty, int number, Throwable cause) {
		super(new StringBuilder("Le nombre de questions de type  ").append(field).append(" et de difficluté ")
				.append(difficulty).append(" est inférieur au nombre requis (").append(Integer.toString(number))
				.append(")").toString(), cause);
	}

	public NotEnoughQuestionsException(String field, Difficulty difficulty, int number) {
		super(new StringBuilder("Le nombre de questions de type  ").append(field).append(" et de difficluté ")
				.append(difficulty).append(" est inférieur au nombre requis (").append(Integer.toString(number))
				.append(")").toString());
	}

	public NotEnoughQuestionsException(String message, NotEnoughQuestionsException e) {
		super(message,e);
	}
}
