package net.bluefsd.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

import net.bluefsd.security.BfWebMvcConfig;

@EnableEurekaClient
@EnableFeignClients(basePackages = "net.bluefsd.security") 
@RestController
@SpringBootApplication
@EnableDiscoveryClient
@Import({ AppConfig.class, BfWebMvcConfig.class })
public class FsdApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		SpringApplication.run(FsdApplication.class, args);
	}
}
