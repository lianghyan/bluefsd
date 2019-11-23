package net.bluefsd.security.controller;

import java.rmi.server.UID;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.bluefsd.comm.controller.BaseController;
import net.bluefsd.entity.BFUser;
import net.bluefsd.security.service.BFUserDetailsService;
import net.bluefsd.security.service.MailService;
import net.bluefsd.util.VerifyCodeUtil;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private BFUserDetailsService authService;

	@Autowired
	MailService mailService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String createToken(@RequestParam String username, @RequestParam String password)
			throws AuthenticationException {
		return authService.login(username, password);
	}

	@RequestMapping(value = "/canaccess", method = RequestMethod.POST)
	public boolean verifyToken(@RequestParam String token) throws AuthenticationException {
		return true;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Map createAccount(BFUser u) throws JsonProcessingException {
		BFUser user = authService.createAccount(u);
		Map map = composeReturnMap();
		map.put("id", user.getId());
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Map updateProfile(BFUser u) throws JsonProcessingException {
		authService.updateProfile(u);
		return composeReturnMap();
	}

	@RequestMapping(value = "/mail", method = RequestMethod.POST)
	public String sendMail(BFUser user) {
		mailService.sendMail(user);
		return "ok";
	}

	@RequestMapping(value = "/activateduseraccount", method = RequestMethod.GET)
	public String verify(String verifyCode) {
		String userName = authService.verify(verifyCode);
		if (userName != null) {
			return "User " + userName + " complete verification successfully!";
		} else {
			return "Invalid verification link";
		}
	}

	public static void main(String args[]) {

		UID uid = new UID();
		System.out.println(uid.toString());
		String code = VerifyCodeUtil.generateVerifyCode(32);

		System.out.println(code);

	}
}
