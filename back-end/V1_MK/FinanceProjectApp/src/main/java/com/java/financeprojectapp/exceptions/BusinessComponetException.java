package com.java.financeprojectapp.exceptions;

public class BusinessComponetException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public BusinessComponetException() {
		super("Error occurred");
	}
	
	public BusinessComponetException(String message) {
		super(message);
	}
	
	public BusinessComponetException(String message, Exception actual) {
		super(message, actual);
	}
}
