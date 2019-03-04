package com.abbeal.recruitwebservice.exceptions;

public class QuizzNotPresentException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7722999896780201043L;

	public QuizzNotPresentException(String id, Throwable cause) {
		super(new StringBuilder("Le quizz spécifié avec le id ").append(id).append(" n'existe pas !").toString(), cause);
	}
	
	public QuizzNotPresentException(String id) {
		super(new StringBuilder("Le quizz spécifié avec le id ").append(id).append(" n'existe pas !").toString());
	}

}
