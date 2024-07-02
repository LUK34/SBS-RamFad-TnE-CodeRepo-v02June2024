package kw.kng.exceptions;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
		description="ErroDetails DTO(Data Transfer Object) to handle exception related messages between CLIENT and SERVER"
)
@Data
@AllArgsConstructor
public class ErrorDetails 
{
	private LocalDateTime timestamp;
	private String message;
	private String details;
	private String errorCode;
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