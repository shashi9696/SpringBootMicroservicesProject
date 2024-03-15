package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.utility.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
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
	
	/*@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(OrderNotFoundException ex) {
		
		String message = ex.getMessage();
		ErrorResponse response = ErrorResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}*/
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceException(OrderNotFoundException exception) {
		ErrorResponse response = ErrorResponse.builder()
				.message(exception.getMessage())
				.success(true)
				.status(HttpStatus.NOT_FOUND)
				.build();
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
