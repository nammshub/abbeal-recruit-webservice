package com.abbeal.recruitwebservice.exceptions;

public class UserNotPresentException extends Exception {

	public UserNotPresentException(String id, Throwable cause) {
		super(new StringBuilder("Le user spécifié avec le id ").append(id).append(" n'existe pas !").toString(), cause);
	}
	
	public UserNotPresentException(String id) {
		super(new StringBuilder("Le user spécifié avec le id ").append(id).append(" n'existe pas !").toString());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8386414165484914555L;

}
