package com.qry.base.util;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = -1284353451163738298L;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable inner) {
		super(message, inner);
	}
}
