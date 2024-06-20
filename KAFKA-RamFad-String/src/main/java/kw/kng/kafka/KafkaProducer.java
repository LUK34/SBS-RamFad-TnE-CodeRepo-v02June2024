package kw.kng.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer 
{
	
	// ---------------------------------------------------------------------------------------------------------------
				//Common Properties
			
			@Value("${spring.kafka.topic.name}")
			private String topicName;
			
			@Value("${spring.kafka.consumer.group-id}")
			private String groupId;
//---------------------------------------------------------------------------------------------------------------

	private static final Logger LOGGER= LoggerFactory.getLogger(KafkaProducer.class);
	
	private KafkaTemplate<String, String> kafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) 
	{
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(String message)
	{
		LOGGER.info(String.format("Message sent %s", message));
		kafkaTemplate.send(topicName ,message);
	}
	

}
/*
 1. What we are doing here is we created a Producer class which uses KafkaTemplate to send a message to Kafka Topic.
 */