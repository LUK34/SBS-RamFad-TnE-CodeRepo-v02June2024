package kw.kng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class McrsrvServiceRegistryV1Application 
{

	public static void main(String[] args)
	{
		SpringApplication.run(McrsrvServiceRegistryV1Application.class, args);
	}

}
