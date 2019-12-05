package net.bluefsd.user;

import java.util.Map;
import java.util.Random;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class UserManager {
	static Random r = new Random(1);

	public static void createUser(Map<String, Object> map) {
		int ui = r.nextInt();
		//map.put("userName", "usky" + ui);
		map.put("userName", "usky");
		map.put("password", enCodePass());
		map.put("email", "liangydl@cn.ibm.com");
		map.put("mobileNumber", "0411-8888");
		map.put("role", "user");

	}

	public static void createAdmin(Map<String, Object> map) {
		int ui = r.nextInt();
		//map.put("userName", "admsky"+ui);
		map.put("userName", "admsky");
		map.put("password", enCodePass());
		map.put("email", "liangydl@cn.ibm.com");
		map.put("mobileNumber", "0411-8888");
		map.put("role", "admin");
	}

	public static void update(Map<String, Object> map) {
		map.put("id", "" + 7);
		map.put("userName", "ustart");
		map.put("password", "111111");
		map.put("email", "liangydl@cn.ibm.com");
		map.put("mobileNumber", "0411-99999");
		map.put("role", "user");
	}

	public static String enCodePass() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "111111";
		String enPwd = encoder.encode(rawPassword);
		System.out.println(enPwd);
		return enPwd;
	}

	public static void main(String[] args) {
		/* 可逆算法 */
		String base64 = "111111";
		byte[] byte64 = Base64.encodeBase64(base64.getBytes(), true);
		System.out.println("after encryption:" + new String(byte64));

		String rev64 = new String(byte64);
		byte[] rev = Base64.decodeBase64(rev64.getBytes());
		System.out.println("after decryption:" + new String(rev));
		
		enCodePass();

	}
}
