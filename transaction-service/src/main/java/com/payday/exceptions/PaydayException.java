package com.payday.exceptions;
import org.springframework.http.HttpStatus;

public class PaydayException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final HttpStatus status;

    public PaydayException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}