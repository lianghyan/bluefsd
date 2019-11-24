package net.bluefsd.company;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({ "net.bluefsd.model",  "net.bluefsd.comm",
		"net.bluefsd.comm.log", "net.bluefsd.*.service", "net.bluefsd.*.controller" })

//@ComponentScan("net.fsd.model, net.fsd.*.service, net.fsd.*.controller, net.fsd.security, net.fsd.comm.log")
@EntityScan("net.bluefsd.entity")
@EnableJpaRepositories({"net.bluefsd.*.dao", "net.bluefsd.dao"})
//@EnableAspectJAutoProxy

//@EnableJpaRepositories(basePackages = {"com.xx","com.yy"})
//@EntityScan(basePackages = {"com.xx","com.yy"})
public class AppConfig {

}
