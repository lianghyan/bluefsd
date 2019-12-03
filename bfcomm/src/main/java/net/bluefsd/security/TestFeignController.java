package net.bluefsd.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/comm")
public class TestFeignController {
	@Autowired
	TokenService tokenService;

	@RequestMapping(value = { "/feign" }, method = RequestMethod.POST)
	public boolean testFeignclient() {
		return tokenService.allowaccess();
	}
}
