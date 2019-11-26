package net.bluefsd.user;

import org.apache.tomcat.util.codec.binary.Base64;
import org.assertj.core.internal.Bytes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.DigestUtils;

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
		// request.param("role", "admin");
	}

	public static String enCodePass() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "111111";
		String enPwd = encoder.encode(rawPassword);
		
		return enPwd;
	}

	public static void main(String[] args) {
		/* 可逆算法 */
		String base64 = "111111";
		byte[] byte64 = Base64.encodeBase64(base64.getBytes(), true);
		System.out.println("加密后:" + new String(byte64));

		String rev64 = new String(byte64);
		byte[] rev = Base64.decodeBase64(rev64.getBytes());
		System.out.println("解密后:" + new String(rev));

	}
}
