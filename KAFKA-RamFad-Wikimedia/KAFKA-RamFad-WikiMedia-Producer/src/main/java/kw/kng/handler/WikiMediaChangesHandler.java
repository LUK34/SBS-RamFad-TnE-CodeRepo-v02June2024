package kw.kng.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

public class WikiMediaChangesHandler implements EventHandler
{
	private static final Logger LOGGER= LoggerFactory.getLogger(WikiMediaChangesHandler.class);
	
	private KafkaTemplate<String, String> kft;
	private String topic;
	
	//Parameterized constructor
	public WikiMediaChangesHandler(KafkaTemplate<String, String> kft, String topic) 
	{
		this.kft = kft;
		this.topic = topic;
	}

	@Override
	public void onOpen() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClosed() throws Exception {
		// TODO Auto-generated method stub
		
	}

	/*
	  This method is the one which we will be using.Whenever there is a new event in wikimedia , this`WikiMediaChangesHandler`
	  will be triggered. Inside `WikiMediaChangesHandler` the `onMessage` method will be triggered and the events will be read.
	  The events will be transferred to the `topic`. Refer the pom.xml file.
	 */
	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws Exception
	{
		
		LOGGER.info(String.format("Event data -> %s", messageEvent.getData()));
		
		kft.send(topic, messageEvent.getData());
	}

	@Override
	public void onComment(String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
