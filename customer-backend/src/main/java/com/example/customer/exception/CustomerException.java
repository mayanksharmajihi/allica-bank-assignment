package com.example.customer.exception;

import lombok.Getter;

@Getter
public class CustomerException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4644286660144884778L;
	private final String errorCode;

    public CustomerException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
