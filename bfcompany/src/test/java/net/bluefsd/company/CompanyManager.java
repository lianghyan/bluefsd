package net.bluefsd.company;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class CompanyManager {
	public static void create_1(MockHttpServletRequestBuilder request) {
		request.param("companyCd", "neu");
		request.param("companyName", "neusoft company");
		request.param("ceoName", "liujiren");
		request.param("brief", "Chinese software company");

	}

	public static void create_2(MockHttpServletRequestBuilder request) {
		request.param("companyCd", "IBM");
		request.param("companyName", "International Business Machine Company");
		request.param("ceoName", "John");
		request.param("brief", "software company");

	}

	public static void update(MockHttpServletRequestBuilder request, int id) {
		request.param("companyCd", "IBM");
		request.param("companyName", "International Business Machine Company");
		request.param("ceoName", "John");
		request.param("brief", "software company");

	}
}
