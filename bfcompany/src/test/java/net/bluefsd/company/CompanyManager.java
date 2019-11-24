package net.bluefsd.company;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class CompanyManager {
	public static void create_1(MockHttpServletRequestBuilder request) {
		request.param("companyCd", "neu");
		request.param("companyName", "neusoft company");
		request.param("ceoName", "liujiren");
		request.param("exchCd", "NSE");
		request.param("brief", "Chinese software company");

	}

	public static void create_2(MockHttpServletRequestBuilder request) {
		request.param("companyCd", "IBM");
		request.param("companyName", "International Business Machine Company");
		request.param("ceoName", "John");
		request.param("exchCd", "NSE");
		request.param("brief", "software company");

	}

	public static void update(MockHttpServletRequestBuilder request, int id) {
		request.param("companyCd", "IBM");
		request.param("companyName", "International Business Machine Company");
		request.param("ceoName", "John");
		request.param("exchCd", "NSE");
		request.param("brief", "software company");

	}

	// (String[] companyCds, String from, String to, String periodType) {
	public static void listPrice(MockHttpServletRequestBuilder request) {
		request.param("stockCds", "500112");
		request.param("from", "2019-05-12");
		request.param("to", "2019-06-12");
		// week, month, p6month
		request.param("periodType", "week");
	}

	public static void createStock(MockHttpServletRequestBuilder request) {
		request.param("stockCd", "500112");
		request.param("companyCd", "neu");
		request.param("exchCd", "NSE");

	}
}
