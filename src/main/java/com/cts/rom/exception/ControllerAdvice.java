
package com.cts.rom.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@RestControllerAdvice
public class ControllerAdvice {
	// Exception Method for USER not found
	
	  @ExceptionHandler(UsernameNotFoundException.class)
	  
	  @ResponseStatus(HttpStatus.NOT_FOUND) public Error
	  userNotFoundException(UsernameNotFoundException userNotFoundException) {
	  return new Error(HttpStatus.NOT_FOUND, LocalDateTime.now(),
	  userNotFoundException.getMessage()); }
	  
	  // Exception for jwt malfunctioned error
	  
	  @ExceptionHandler(MalformedJwtException.class)
	  
	  @ResponseStatus(HttpStatus.UNAUTHORIZED) public Error
	  tokenMalformedException() { return new Error(HttpStatus.UNAUTHORIZED,
	  LocalDateTime.now(), "Not Authorized --> Token is Invalid.."); }
	  
	  // Exception for JWT Signature unauthorized error
	  
	  @ExceptionHandler(SignatureException.class)
	  
	  @ResponseStatus(HttpStatus.UNAUTHORIZED) public Error
	  tokenSignatureException() { return new Error(HttpStatus.UNAUTHORIZED,
	  LocalDateTime.now(), "Not Authorized --> Token is Invalid.."); }
	 
	
	 @ExceptionHandler(Exception.class)
	    public final ResponseEntity<Error> handleAllExceptions(Exception ex, WebRequest request) {
			/*
			 * List<String> details = new ArrayList<>();
			 * details.add(ex.getLocalizedMessage()); // Errormsg error = new
			 * Errormsg("Server Error", details);
			 */	        
	        Error error=new Error(HttpStatus.INTERNAL_SERVER_ERROR,LocalDateTime.now(),"internal server error");
	        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
