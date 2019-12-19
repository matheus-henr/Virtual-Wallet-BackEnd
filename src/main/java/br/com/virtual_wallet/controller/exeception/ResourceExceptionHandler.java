package br.com.virtual_wallet.controller.exeception;



import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.virtual_wallet.service.exeception.AuthorizationException;
import br.com.virtual_wallet.service.exeception.CreationInvalidException;
@ControllerAdvice
public class ResourceExceptionHandler {


	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> objectNotFound(AuthorizationException e,
			HttpServletRequest request) {
		
		StandardError err = new StandardError(e.getMessage(),HttpStatus.NOT_FOUND.value(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(CreationInvalidException.class)
	public ResponseEntity<StandardError> creationIvalid(AuthorizationException e,
			HttpServletRequest request) {
		
		StandardError err = new StandardError(e.getMessage(),HttpStatus.NOT_FOUND.value(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
}
