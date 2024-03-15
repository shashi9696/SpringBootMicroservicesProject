package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.utility.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/*@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Map<String, Object>> notFoundHandler(ProductNotFoundException ex) {
		Map map = new HashMap();
		map.put("message", ex.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}*/
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(ProductNotFoundException ex) {
		
		String message = ex.getMessage();
		ErrorResponse response = ErrorResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}

}
