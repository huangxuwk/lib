package com.dl.spring_imitate.core;

public class HasSurplusBeanMethodException extends RuntimeException {
	private static final long serialVersionUID = 2690373283484106089L;

	public HasSurplusBeanMethodException() {
	}
	
	public HasSurplusBeanMethodException(String message) {
		super(message);
	}

	public HasSurplusBeanMethodException(Throwable cause) {
		super(cause);
	}

	public HasSurplusBeanMethodException(String message, Throwable cause) {
		super(message, cause);
	}

	public HasSurplusBeanMethodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
