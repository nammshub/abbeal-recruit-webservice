package com.abbeal.recruitwebservice.exceptions;

public class InvalidJwtAuthenticationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6682304025101931900L;
	
	public InvalidJwtAuthenticationException(String cause) {
		super(cause);
	}

}
