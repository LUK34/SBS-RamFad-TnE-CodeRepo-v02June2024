package kw.kng.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import kw.kng.dto.OrderEvent;

@Service
public class OrderConsumer 
{
	private static final Logger LOGGER=LoggerFactory.getLogger(OrderConsumer.class);

	@KafkaListener(topics="${spring.kafka.topic.name}",
								groupId="${spring.kafka.consumer.group-id}")
	public void consume(OrderEvent event) //`OrderEvent` will be from Base-domain
	{
		LOGGER.info(String.format("Order event received in Stock service => %s", event.toString()));
		
		//save the order event into the database
		
		
	}
}



