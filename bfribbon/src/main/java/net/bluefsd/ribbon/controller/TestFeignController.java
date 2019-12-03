package net.bluefsd.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/comm")
public class TestFeignController {
	@Autowired
	TokenService tokenService;

	@RequestMapping(value = { "/feign" }, method = RequestMethod.POST)
	public String testFeignclient() {
		return tokenService.allowaccess();
	}
	
	@RequestMapping(value = { "/say" }, method = RequestMethod.POST)
	public String sayHello() {
		return "Hello from test feign";
	}
}
