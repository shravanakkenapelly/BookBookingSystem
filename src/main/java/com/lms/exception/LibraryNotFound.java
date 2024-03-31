package com.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class LibraryNotFound extends RuntimeException {
	
	private String message;
	public LibraryNotFound(String message) {
		super();
		this.message=message;
	}

}
