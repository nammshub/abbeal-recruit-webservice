package com.abbeal.recruitwebservice.exceptions;

public class MailAlreadyPresentException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5788345885218682473L;

	public MailAlreadyPresentException(String mail, Throwable cause) {
		super(String.format("Le mail %s est déjà enregistré", mail), cause);
	}
	
	public MailAlreadyPresentException(String mail) {
		super(String.format("Le mail %s est déjà enregistré", mail));
	}
}
