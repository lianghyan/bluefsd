package net.bluefsd.eurek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication

public class EurekaSvrMain {
	public static void main(String[] args) {
		SpringApplication.run(EurekaSvrMain.class, args);
	}
}
