package com.tao.taskManager.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
	private HttpStatus status;
	private String message;
	private Map<String, String> details;
	
	
}
