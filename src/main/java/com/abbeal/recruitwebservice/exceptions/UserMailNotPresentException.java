package com.abbeal.recruitwebservice.exceptions;

public class UserMailNotPresentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8664965180278718131L;

	public UserMailNotPresentException(String mail, Throwable cause) {
		super(new StringBuilder("Il n'existe aucun utilisateur avec le mail : ").append(mail).toString(), cause);
	}
	
	public UserMailNotPresentException(String mail) {
		super(new StringBuilder("Il n'existe aucun utilisateur avec le mail :").append(mail).toString());
	}
}
