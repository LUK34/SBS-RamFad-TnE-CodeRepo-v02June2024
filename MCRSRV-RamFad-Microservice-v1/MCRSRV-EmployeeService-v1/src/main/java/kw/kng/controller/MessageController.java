package kw.kng.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RestController
@RequestMapping("/api/es-m")
public class MessageController 
{
	@Value("${spring.boot.message}")
	private String message;

	@GetMapping("/empl-message")
	public String message()
	{
		return message;
	}
	
}


/*
URL:  localhost:8081/api/es-m/empl-message
 */