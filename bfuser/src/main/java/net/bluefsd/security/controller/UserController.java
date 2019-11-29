package net.bluefsd.security.controller;

import java.rmi.server.UID;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Map createToken(@RequestBody BFUser u)
			throws AuthenticationException {
		try {
			String token = authService.login(u.getUserName(), u.getPassword());
			return composeReturnMap("token", token);
		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Map createAccount(@RequestBody BFUser u) throws JsonProcessingException {
		if (u.getUserName() == null || u.getPassword() == null) {
			return composeErrorMap("user name or password is null!");
		}
		try {
			BFUser user = authService.createAccount(u);
			Map map = composeReturnMap("Account is saved successfully!");
			map.put("id", user.getId());
			return map;

		} catch (Exception ex) {
			String msg = ex.getMessage();
			return composeErrorMap(msg);
		}

	}

	@RequestMapping(value = "/canaccess", method = RequestMethod.POST)
	public boolean verifyToken(@RequestParam String token) throws AuthenticationException {
		return true;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Map updateProfile(@RequestBody BFUser u) throws JsonProcessingException {
		authService.saveOrUpdateProfile(u);
		return composeReturnMap();
	}

	@RequestMapping(value = "/mail", method = RequestMethod.POST)
	public Map sendMail(BFUser u) {
		authService.saveOrUpdateProfile(u);
		mailService.sendMail(u);
		return composeReturnMap();

	}

	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public Map activate(String verifyCode) {
		String userName = authService.verify(verifyCode);
		if (userName != null) {
			return composeReturnMap(
					"User " + userName + " complete verification successfully! You can signin with your account now.");
		} else {
			return composeErrorMap("Invalid verification link");
		}
	}

	public static void main(String args[]) {

		UID uid = new UID();
		System.out.println(uid.toString());
		String code = VerifyCodeUtil.generateVerifyCode(32);

		System.out.println(code);

	}
}
