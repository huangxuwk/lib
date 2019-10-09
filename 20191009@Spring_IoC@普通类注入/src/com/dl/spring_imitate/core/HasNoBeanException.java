package com.dl.spring_imitate.core;

public class HasNoBeanException extends RuntimeException {
	private static final long serialVersionUID = 3740910523878908082L;

	public HasNoBeanException() {
	}
	
	public HasNoBeanException(String message) {
		super(message);
	}
	
	public HasNoBeanException(Throwable cause) {
		super(cause);
	}
	
	public HasNoBeanException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public HasNoBeanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
