package kw.kng.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import kw.kng.payload.User;

@Service
public class JsonKafkaProducer 
{
	// ---------------------------------------------------------------------------------------------------------------
			//Common Properties
		
		@Value("${spring.kafka.topic.name}")
		private String topicName;
		
		@Value("${spring.kafka.consumer.group-id}")
		private String groupId;

// ---------------------------------------------------------------------------------------------------------------

	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);
	
	private KafkaTemplate<String, User> kafkaTemplate;

	public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) 
	{
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(User data)
	{
		LOGGER.info(String.format("Message sent -> %s", data.toString()));
		
		Message<User> message = MessageBuilder.withPayload(data)
																			//	.setHeader(KafkaHeaders.TOPIC, "javaguides_json")
																				.setHeader(KafkaHeaders.TOPIC, topicName)
																				.build();
		kafkaTemplate.send(message);
	}
	
	
}


/*
1. What we are doing here is we created a Producer class which uses KafkaTemplate to send a message as JSON to Kafka Topic.


 Type: POST
 Url: http://localhost:8080/api/v1/kafka/publish
 
 {
 	"id":1,
 	"firstName": "Ramesh",
 	"lastName": "Fadatare"
 }
 
 
*/














