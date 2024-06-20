package kw.kng.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import kw.kng.payload.User;

@Service
public class JsonKafkaConsumer 
{
	// ---------------------------------------------------------------------------------------------------------------
			//Common Properties
		
		@Value("${spring.kafka.topic.name}")
		private String topicName;
		
		@Value("${spring.kafka.consumer.group-id}")
		private String groupId;

// ---------------------------------------------------------------------------------------------------------------


	private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);


	@KafkaListener(topics="${spring.kafka.topic.name}", groupId="${spring.kafka.consumer.group-id}")//from properties file
	public void consume(User user)
	{
		LOGGER.info(String.format("Json message received -> %s", user.toString()));
	}
	
	
}
