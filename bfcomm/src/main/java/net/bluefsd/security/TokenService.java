package net.bluefsd.security;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service(value = "tokenService")
@FeignClient(value = "bfuser",  configuration = {FeignTokenInterceptor.class})
public interface TokenService {
	@RequestMapping(value = "/user/canaccess", method = RequestMethod.POST)
	public boolean allowaccess();
}
