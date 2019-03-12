package com.abbeal.recruitwebservice.exceptions;

public class AnswerNotPresentException extends Exception {

	private static final long serialVersionUID = 7722999896780201043L;

	public AnswerNotPresentException(String id, Throwable cause) {
		super(new StringBuilder("La réponse spécifiée avec le id ").append(id).append(" n'existe pas !").toString(), cause);
	}
	
	public AnswerNotPresentException(String id) {
		super(new StringBuilder("La réponse spécifiée avec le id ").append(id).append(" n'existe pas !").toString());
	}

}
