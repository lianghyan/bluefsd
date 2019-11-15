package bet.bluefsd.stockexch;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class StockExchManager {
	public static void createUser(MockHttpServletRequestBuilder request) {
		request.param("userName", "usky");
		request.param("email", "liangydl@cn.ibm.com");
		request.param("mobileNumber", "0411-8888");
		request.param("role", "user");

	}

	public static void createAdmin(MockHttpServletRequestBuilder request) {
		request.param("userName", "admsky");
		request.param("email", "liangydl@cn.ibm.com");
		request.param("mobileNumber", "0411-8888");
		request.param("role", "admin");

	}

	public static void update(MockHttpServletRequestBuilder request, int userId) {
		request.param("id", "" + 2);
		request.param("userName", "usky");
		request.param("email", "liangydl@cn.ibm.com");
		request.param("mobileNumber", "0411-99999");
		// request.param("role", "admin");
	}
}
