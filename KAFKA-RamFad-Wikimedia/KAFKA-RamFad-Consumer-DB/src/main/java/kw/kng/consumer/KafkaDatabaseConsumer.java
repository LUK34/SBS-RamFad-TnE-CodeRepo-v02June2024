package kw.kng.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import kw.kng.entities.WikiMediaData;
import kw.kng.repo.WikiMediaDataRepo;

@Service
public class KafkaDatabaseConsumer 
{
	private static final Logger LOGGER= LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	
	private WikiMediaDataRepo wrepo;
	
	//Constructor Injection
	public KafkaDatabaseConsumer(WikiMediaDataRepo wrepo) 
	{
		this.wrepo = wrepo;
	}
	
	// ---------------------------------------------------------------------------------------------------------------
	//Common Properties

		@Value("${spring.kafka.topic.name}")
		private String topicName;
	//---------------------------------------------------------------------------------------------------------------
	
	@KafkaListener(
			topics="${spring.kafka.topic.name}",
			groupId=	"${spring.kafka.consumer.group-id}"
			)
	public void consume(String eventMessage)
	{
		LOGGER.info(String.format("Event Message received -> %s", eventMessage));
		
		WikiMediaData wm= new WikiMediaData();
		wm.setWikiEventData(eventMessage);
		
		wrepo.save(wm);
	}
	

}
