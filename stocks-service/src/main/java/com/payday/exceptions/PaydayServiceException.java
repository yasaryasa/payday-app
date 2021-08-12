package com.payday.exceptions;

public class PaydayServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String message;

	public PaydayServiceException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
