package kw.kng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.kafka.JsonKafkaProducer;
import kw.kng.payload.User;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController 
{
	private JsonKafkaProducer jkp;

	public JsonMessageController(JsonKafkaProducer jkp) 
	{
		this.jkp = jkp;
	}
	
	@PostMapping("/publish")
	public ResponseEntity<String> publish(@RequestBody User user)
	{
		jkp.sendMessage(user);
		
		return ResponseEntity.ok("Json message sent to Kafka topic");
	}
	

}


/*

 Type: POST
 Url: http://localhost:8080/api/v1/kafka/publish
 
 {
 	"id":1,
 	"firstName": "Ramesh",
 	"lastName": "Fadatare"
 } 
 
 
 
 
 */















