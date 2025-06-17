package com.example.productinventorysystem.exception;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<Map<String, Object>> handlerResourceNotFound(ResourceNotFound exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
				"timestamp",LocalDateTime.now(),
				"status",404,
				"error","Not Found",
				"message",exception.getMessage()
				));
		
	}
	
	@ExceptionHandler(InvalidUserInput.class)
	public ResponseEntity<Map<String, Object>> handlerNotFoundInvailUserINput(InvalidUserInput exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
				"timestamp",LocalDateTime.now(),
				"status",400,
				"error","Invalid User Input",
				"message",exception.getMessage()
				));
		
	}
	
	@ExceptionHandler(InputNotExist.class)
	public ResponseEntity<Map<String, Object>> handlerForUserInputNotExist(InputNotExist exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
				"timestamp",LocalDateTime.now(),
				"status",400,
				"error","User Input Not Exist",
				"message",exception.getMessage()
				));
	}

}
