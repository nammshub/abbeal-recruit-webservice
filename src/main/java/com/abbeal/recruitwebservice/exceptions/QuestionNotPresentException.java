package com.abbeal.recruitwebservice.exceptions;

public class QuestionNotPresentException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7722999896780201043L;

	public QuestionNotPresentException(String id, Throwable cause) {
		super(new StringBuilder("La question spécifiée avec le id ").append(id).append(" n'existe pas !").toString(), cause);
	}
	
	public QuestionNotPresentException(String id) {
		super(new StringBuilder("La question spécifiée avec le id ").append(id).append(" n'existe pas !").toString());
	}

}