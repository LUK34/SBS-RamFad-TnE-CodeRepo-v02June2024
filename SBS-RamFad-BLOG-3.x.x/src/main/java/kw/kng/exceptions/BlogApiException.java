package kw.kng.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BlogApiException extends RuntimeException
{
	private String message;

	public BlogApiException(String message) 
	{
		super(message);
	}
	
	

}
