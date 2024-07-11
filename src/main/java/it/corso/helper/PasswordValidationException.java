package it.corso.helper;

public class PasswordValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L; //estendiamo una classe d'eccezione
	private final String message ="Password inadeguata";
	
	public String getMessage() {
		return message;
	}
}
