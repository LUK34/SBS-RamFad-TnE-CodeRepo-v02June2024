package kw.kng.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer 
{
	// ---------------------------------------------------------------------------------------------------------------
			//Common Properties
		
		@Value("${spring.kafka.topic.name}")
		private String topicName;
		
		@Value("${spring.kafka.consumer.group-id}")
		private String groupId;
//---------------------------------------------------------------------------------------------------------------

	private static final Logger LOGGER=LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics="${spring.kafka.topic.name}", groupId="${spring.kafka.consumer.group-id}")//from properties file
	public void consume(String message)
	{
		LOGGER.info(String.format("Message received -> %s", message));
	}
}
