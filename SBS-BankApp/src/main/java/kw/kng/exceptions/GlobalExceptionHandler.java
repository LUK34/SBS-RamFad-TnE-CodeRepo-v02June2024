package kw.kng.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler 
{

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
																			//Handle specific exception - AccountException
	
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<ErrorDetails> handleAccountException(AccountException ex,	 WebRequest webRequest)
	{
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
																				  ex.getMessage(),
																				  webRequest.getDescription(false),
																				  "ACCOUNT_NOT_FOUND");
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
																			//Handle specific exception - InsufficentAmountException
	
	@ExceptionHandler(InsufficentAmountException.class)
	public ResponseEntity<ErrorDetails> handleInsufficentAmountException(InsufficentAmountException ia, WebRequest webRequest)
	{
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				  																 ia.getMessage(),
				  																 webRequest.getDescription(false),
				  																 "INSUFFICENT_AMOUNT_IN_ACCOUNT");

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
																					//Handle Generic Exception -Working Code
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGenericException(Exception ex, WebRequest webRequest)
	{
		ErrorDetails err= new ErrorDetails(LocalDateTime.now(),
																  ex.getMessage(),
																  webRequest.getDescription(false),
																  "GENERIC_EXCEPTION_->_INTERNAL_SERVER_ERROR");
				
		return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
}


/*
Exception Handling:
-----------------------------

1. Handling exceptions in Spring Boot Rest APIs is typically done using the @ControllerAdvice and @ExceptionHandler annotations.

2. @ControllerAdvice enables global exception handling for controllers.

3. @ExceptionHandler defines methods to handle specific exceptions withing a controller or globally with @ControllerAdvice.

Development Steps:
----------------------------
1. Create custom exception named AccountException.

2.Using AccountException.

3. Create ErrorDetails class to hold error response.

4. Create GlobalExceptionHandler to handle specific exception as well as generic exceptions.


*/
