package net.bluefsd.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

//@EnableEurekaClient
@RestController
@SpringBootApplication
@Import({AppConfig.class })
public class FsdApplication {
 
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();		
		SpringApplication.run(FsdApplication.class, args);
	}
}
