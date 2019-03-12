package com.abbeal.recruitwebservice.exceptions;

public class QuizzInstanceNotPresentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7722999896780201043L;

	public QuizzInstanceNotPresentException(long id, Throwable cause) {
		super(new StringBuilder("La quizz instance spécifiée avec le id ").append(id).append(" n'existe pas !").toString(), cause);
	}
	
	public QuizzInstanceNotPresentException(long id) {
		super(new StringBuilder("La quizz instance spécifiée avec le id ").append(id).append(" n'existe pas !").toString());
	}

}
