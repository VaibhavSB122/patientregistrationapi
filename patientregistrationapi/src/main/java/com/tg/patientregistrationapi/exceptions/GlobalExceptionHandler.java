package com.tg.patientregistrationapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tg.patientregistrationapi.dtos.ResponseWrapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<ResponseWrapper> handlePatientNotFoundException
	(PatientNotFoundException exception){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseWrapper(exception.getMessage()));
	}
	
	@ExceptionHandler(PatientAlreadyExistsException.class)
	public ResponseEntity<ResponseWrapper> handlePatientAlreadyExistsException(PatientAlreadyExistsException exception){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseWrapper(exception.getMessage()));
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseWrapper> handleRuntimeException(RuntimeException exception){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseWrapper(exception.getMessage()));
	}
	
	

}
