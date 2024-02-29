package com.arphoenix.microservice.exception;

import java.io.Serial;
import java.io.Serializable;

public class NotFoundException extends Exception implements Serializable {
	@Serial
	private static final long serialVersionUID = -6762391678258643789L;

	public NotFoundException(String message) {
		super(message);
	}
}