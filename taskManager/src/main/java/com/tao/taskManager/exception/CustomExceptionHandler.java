package com.tao.taskManager.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> exception(Exception exception) {
		ErrorDetails errorDetails= new ErrorDetails();
		errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorDetails.setMessage(exception.getMessage());
		return  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> taskNotFound(ResourceNotFoundException ex) {
		ErrorDetails errorDetails= new ErrorDetails();
		errorDetails.setStatus(HttpStatus.NOT_FOUND);
		errorDetails.setMessage(ex.getMessage());
		return  new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	//@ResponseBody
	public ResponseEntity<ErrorDetails> validationError(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap= new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(
				error -> {
					errorMap.put(error.getField(), error.getDefaultMessage());
				});
		
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "Validation failure", errorMap);
		return  new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetails> handleIllegalArgumentException(MethodArgumentTypeMismatchException ex) {
		ErrorDetails errorDetails= new ErrorDetails();
		errorDetails.setStatus(HttpStatus.BAD_REQUEST);
		errorDetails.setMessage("Please pass valid request");
		return  new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	
	

}
