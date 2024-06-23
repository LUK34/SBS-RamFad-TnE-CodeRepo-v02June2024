package kw.kng.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig 
{
	// ---------------------------------------------------------------------------------------------------------------
		//Common Properties
	
	@Value("${spring.kafka.topic.name}")
	private String topicName;
	/*
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	 */
//---------------------------------------------------------------------------------------------------------------

	@Bean
	public NewTopic topic()
	{
	return TopicBuilder.name(topicName).build();
			                     
	}


}
