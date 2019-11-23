package net.bluefsd.user;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class UserManager {
	public static void createUser(MockHttpServletRequestBuilder request) {
		request.param("userName", "usky");
		request.param("password", enCodePass());
		request.param("email", "liangydl@cn.ibm.com");
		request.param("mobileNumber", "0411-8888");
		request.param("role", "user");

	}

	public static void createAdmin(MockHttpServletRequestBuilder request) {
		request.param("userName", "admsky");
		request.param("password", enCodePass());
		request.param("email", "liangydl@cn.ibm.com");
		request.param("mobileNumber", "0411-8888");
		request.param("role", "admin");
 	}

	public static void update(MockHttpServletRequestBuilder request, int userId) {
		request.param("id", "" + 2);
		request.param("userName", "usky");
		request.param("password", enCodePass());
		request.param("email", "liangydl@cn.ibm.com");
		request.param("mobileNumber", "0411-99999");
		//request.param("role", "admin");
	}

	public static String enCodePass() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "111111";
		String enPwd = encoder.encode(rawPassword);
		return enPwd;
	}
}
