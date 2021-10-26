package com.revobank.exceptions;

public class ForbiddenArgumentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ForbiddenArgumentException(String msg) {
		super(msg);
	}
}
