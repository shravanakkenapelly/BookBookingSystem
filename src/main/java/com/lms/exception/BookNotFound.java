package com.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFound extends RuntimeException{
	
	
	private String message;
	
	public BookNotFound(String message){
		super();
		this.message=message;
	}

}
