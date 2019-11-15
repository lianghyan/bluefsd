package net.bluefsd.stockexch;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({ "net.bluefsd.model", /*"net.bluefsd.security.cfg",*/ "net.bluefsd.security.filter", "net.bluefsd.comm",
		"net.bluefsd.comm.log", "net.bluefsd.security.service", "net.bluefsd.security.controller" })

//@ComponentScan("net.fsd.model, net.fsd.*.service, net.fsd.*.controller, net.fsd.security, net.fsd.comm.log")
@EntityScan("net.bluefsd.entity")
@EnableJpaRepositories("net.bluefsd.security.dao")
//@EnableAspectJAutoProxy

//@EnableJpaRepositories(basePackages = {"com.xx","com.yy"})
//@EntityScan(basePackages = {"com.xx","com.yy"})
public class AppConfig {

}
