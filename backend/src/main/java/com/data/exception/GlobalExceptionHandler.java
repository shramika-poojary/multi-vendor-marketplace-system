package com.data.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> handle(MethodArgumentNotValidException m){
	 Map<String,String>map=new HashMap<String, String>();
	 m.getBindingResult().getFieldErrors().forEach(e->map.put(e.getField(),e.getDefaultMessage()));
	  return map;
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handle(NullPointerException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handle(ResourceNotFoundException rs){
		return new ResponseEntity<String>(rs.getMessage(),HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> handle(InvalidCredentialsException cs){
		return new ResponseEntity<String>(cs.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException rn) {
        return new ResponseEntity<>(rn.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
}
