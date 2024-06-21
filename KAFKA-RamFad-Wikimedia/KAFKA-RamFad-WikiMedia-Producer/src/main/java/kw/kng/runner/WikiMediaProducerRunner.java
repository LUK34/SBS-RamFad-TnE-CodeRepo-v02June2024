package kw.kng.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import kw.kng.producer.WikiMediaChangesProducer;

@Component
public class WikiMediaProducerRunner implements CommandLineRunner
{
	@Autowired
	private WikiMediaChangesProducer wmcp;
	
	@Override
	public void run(String... args) throws Exception 
	{
		System.out.println("WikiMediaProducerRunner.run() :: Initiated the WikiMedia Producer in the Runner Class::");
		wmcp.sendMessage();
		
	}
	

}
