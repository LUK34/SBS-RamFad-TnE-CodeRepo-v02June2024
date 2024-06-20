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
	
																			//Handle specific exception - ResourceNotFoundException
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleAccountException(ResourceNotFoundException ex,	 WebRequest webRequest)
	{
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
																				  ex.getMessage(),
																				  webRequest.getDescription(false),
																				  "RESOURCE_NOT_FOUND");
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
															//Handle specific exception - PaymentException

	@ExceptionHandler(PaymentException.class)
	public ResponseEntity<ErrorDetails> handleAccountException(PaymentException ex,	 WebRequest webRequest)
	{
	ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
			  ex.getMessage(),
			  webRequest.getDescription(false),
			  "PAYMENT_TYPE_INCONSISTANCY");
	
	return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
		
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
																					//Handle Generic Exception
	
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
1. Create custom exception named.

2.Use that custom exception that was created.

3. Create ErrorDetails class to hold error response.

4. Create GlobalExceptionHandler to handle specific exception as well as generic exceptions.


*/
