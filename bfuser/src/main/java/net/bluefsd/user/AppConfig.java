package net.bluefsd.user;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({ "net.bluefsd.model",  "net.bluefsd.*.cfg", "net.bluefsd.*.filter", "net.bluefsd.comm",
		"net.bluefsd.comm.log", "net.bluefsd.user.service", "net.bluefsd.user.controller" })

@EntityScan("net.bluefsd.entity")
@EnableJpaRepositories("net.bluefsd.*.dao")
//@EnableAspectJAutoProxy

//@EnableJpaRepositories(basePackages = {"com.xx","com.yy"})
//@EntityScan(basePackages = {"com.xx","com.yy"})
public class AppConfig {

}
