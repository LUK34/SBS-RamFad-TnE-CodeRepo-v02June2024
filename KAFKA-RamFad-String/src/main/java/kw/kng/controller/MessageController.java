package kw.kng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.kafka.KafkaProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController 
{
	private KafkaProducer kfp;

	public MessageController(KafkaProducer kfp)
	{
		super();
		this.kfp = kfp;
	}
	
	//http:localhost:8080/api/v1/kafka/publish?message=hello world
	@GetMapping("/publish")
	public ResponseEntity<String> publish(@RequestParam("message") String message)
	{
		kfp.sendMessage(message);
		return ResponseEntity.ok("Message sent to the topic");
	}
}
