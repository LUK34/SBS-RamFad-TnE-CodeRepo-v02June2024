package kw.kng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
//@EnableEurekaClient -> Only for 2.x.x , for 3.x.x this is autoconfigured
public class McrsrvConfigServerV1Application {

	public static void main(String[] args) {
		SpringApplication.run(McrsrvConfigServerV1Application.class, args);
	}

}
