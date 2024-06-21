package kw.kng.producer;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

import kw.kng.handler.WikiMediaChangesHandler;

@Service
public class WikiMediaChangesProducer 
{
	// ---------------------------------------------------------------------------------------------------------------
			//Common Properties
		
		@Value("${spring.kafka.topic.name}")
		private String topicName;
		/*
		@Value("${spring.kafka.consumer.group-id}")
		private String groupId;
		*/
		@Value("${wikimedia.url}")
		private String wikimediaUrl;
		
		@Value("${time.value}")
		private Long timeValue;
	//---------------------------------------------------------------------------------------------------------------

	private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangesProducer.class);
	
	private KafkaTemplate<String, String> kafkaTemplate;

	public WikiMediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) 
	{
		this.kafkaTemplate = kafkaTemplate;
	}
	
	//---------------------------------------------------------------------------------------------------------------
	
	
	public void sendMessage() throws InterruptedException
	{
		String topic=topicName;
		
		// to read real time stream data from wikimedia, we use event source
		/*
		 WikiMediaChangesHandler -> onMessage 
		 */
		EventHandler eventHandler = new WikiMediaChangesHandler(kafkaTemplate, topic);
		EventSource.Builder builder= new EventSource.Builder(eventHandler, URI.create(wikimediaUrl));
		EventSource eventSource= builder.build();
		eventSource.start();
		
		TimeUnit.MINUTES.sleep(timeValue);
	}
	
	
}
