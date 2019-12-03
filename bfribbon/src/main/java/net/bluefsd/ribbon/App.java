package net.bluefsd.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@EnableFeignClients(basePackages = "net.bluefsd.ribbon.controller") 
@RestController
@SpringBootApplication
@EnableDiscoveryClient
//@RibbonClient(name = "bfuser", configuration = RibbonConfiguration.class)
//@ComponentScan({ "net.bluefsd.ribbon"})
public class App 
{
    public static void main( String[] args )
    {
		SpringApplication.run(App.class, args);

    }
}
