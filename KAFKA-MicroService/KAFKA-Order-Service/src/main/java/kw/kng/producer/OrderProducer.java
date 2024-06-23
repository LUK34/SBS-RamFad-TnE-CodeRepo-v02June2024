package kw.kng.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import kw.kng.dto.OrderEvent;

@Service
public class OrderProducer
{
	private static final Logger LOGGER= LoggerFactory.getLogger(OrderProducer.class);
	
	private NewTopic topic;
	
	private KafkaTemplate<String, OrderEvent> kafkaTemplate; 
	// You need to add dependency to import `OrderEvent`  which is present in Base-domain. 
	// Base-Domain must be added as a dependency in KAFKA-Order-Service Project.
	
	/*
	 	Input- String , 	Output- OrderEvent
	 */

	public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) 
	{
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(OrderEvent event)//`OrderEvent` will be from Base-domain
	{
		LOGGER.info(String.format("Order Event => %s", event.toString())); //`OrderEvent` will be from Base-domain
		
		//create Message
		Message<OrderEvent> message = MessageBuilder	.withPayload(event)
																							.setHeader(KafkaHeaders.TOPIC, topic.name()) //`OrderEvent` will be from Base-domain
																							.build();
		
		kafkaTemplate.send(message);
	}
	
	
}
