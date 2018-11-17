package com.example.rest.webservices.Exceptions;



import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.rest.webservices.user.UserNotFoundException;


//now we want this to be applied to all the controllers in the project---@ControllerAdvice
//we make is @RestController since it will send a response


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	//this will handle all exceptions
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex,WebRequest request){
		
		ExceptionResponse response=
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
			
		
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//this is to handle user not found
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex,WebRequest request){
		
		ExceptionResponse response=
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
			
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}


	
	//this is to handle Bad request
	//override the method "handleMethodArgumentNotValid" from the abstract class
	//now customize this default method
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		ExceptionResponse response=
				new ExceptionResponse(new Date(), "Validation failed",ex.getBindingResult().toString());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	
}
