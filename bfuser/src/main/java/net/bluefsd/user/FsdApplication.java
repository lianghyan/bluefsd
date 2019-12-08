package net.bluefsd.user;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@Import({AppConfig.class })
public class FsdApplication {
 
	public static void main(String[] args) {
 		SpringApplication.run(FsdApplication.class, args);
	}
	@RequestMapping(value = "/sayHello", method = RequestMethod.POST)
	public String sayHello(String verifyCode) {		 
		
		return "hello verifyCode";
 	}
}
