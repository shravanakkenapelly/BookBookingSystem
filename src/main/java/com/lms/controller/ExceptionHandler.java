package com.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.lms.exception.InvalidDetails;
import com.lms.exception.UserAlreadyExists;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = UserAlreadyExists.class)
	   public ResponseEntity<Object> exception(UserAlreadyExists exception) {
	      return new ResponseEntity<>("User Already Exists", HttpStatus.CONFLICT);
	   }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidDetails.class)
	   public ResponseEntity<Object> exception(InvalidDetails exception) {
	      return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
	   }

}
