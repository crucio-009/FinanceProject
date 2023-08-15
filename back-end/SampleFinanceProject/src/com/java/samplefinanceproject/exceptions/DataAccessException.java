package com.java.samplefinanceproject.exceptions;

public class DataAccessException extends Exception{

	private static final long serialVersionUID = 1L;
	public DataAccessException() {
		super("Error occurred");
	}
	public DataAccessException(String message) {
		super(message);
	}
	public DataAccessException(String message, Exception actual) {
		super(message, actual);
	}
}