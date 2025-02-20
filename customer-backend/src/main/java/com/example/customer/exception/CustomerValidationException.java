package com.example.customer.exception;

public class CustomerValidationException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6909531591087404412L;

	public CustomerValidationException(String message) {
        super(message);
    }
}
