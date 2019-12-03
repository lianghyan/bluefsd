package net.bluefsd.ribbon.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service(value = "tokenService")
@FeignClient(value = "bfuser",   configuration = {FeignTokenInterceptor.class})
public interface TokenService {
	@RequestMapping(value = "/user/canaccess", method = RequestMethod.POST)
	public String allowaccess();}
