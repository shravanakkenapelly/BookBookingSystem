package com.lms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidationHandler {
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> map=new HashMap();
		ex.getAllErrors().stream().forEach(
				r->{
					String fieldname=((FieldError)r).getField();
					String errorMessage=r.getDefaultMessage();
					map.put(fieldname, errorMessage);
				}
				);
		return new ResponseEntity<Object>(map,HttpStatus.OK);
		
	}

}
