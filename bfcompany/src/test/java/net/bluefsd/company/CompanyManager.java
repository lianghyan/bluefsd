package net.bluefsd.company;

import java.util.Map;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class CompanyManager {
	public static void create_1(Map<String, Object> map) {
		map.put("companyCd", "neu");
		map.put("companyName", "neusoft company");
		map.put("ceoName", "liujiren");
		map.put("exchCd", "NSE");
		map.put("brief", "Chinese software company");
		map.put("sectorCd", "0340");

	}

	public static void create_2(Map<String, Object> map) {
		map.put("companyCd", "IBM");
		map.put("companyName", "International Business Machine Company");
		map.put("ceoName", "John");
		map.put("exchCd", "NSE");
		map.put("brief", "software company");
		map.put("sectorCd", "0340");

	}

	public static void update(Map<String, Object> map) {
		map.put("companyCd", "IBM");
		map.put("companyName", "International Business Machine Company");
		map.put("ceoName", "John");
		map.put("exchCd", "NSE");
		map.put("brief", "software company");
		map.put("sectorCd", "0340");
	}

	// (String[] companyCds, String from, String to, String periodType) {
	public static void listPrice(MockHttpServletRequestBuilder request) {
		request.param("stockCds", "500112", "600116");
		request.param("fromDate", "2019-05-12");
		request.param("toDate", "2019-07-12");
		// week, month, p6month
		// request.param("intervalType", "week");
	}

	// (String[] companyCds, String from, String to, String periodType) {
	public static void findPrice(MockHttpServletRequestBuilder request) {
		request.param("stockCd", "500112");
		// week, month, p6month
		// request.param("intervalType", "week");
	}

	public static void createStock(MockHttpServletRequestBuilder request) {
		request.param("stockCd", "512012");
		request.param("companyCd", "kuihuayaoye");
		request.param("exchCd", "NSE");
		request.param("brief", "software company");
		request.param("sectorCd", "0340");

	}

	// (String[] companyCds, String from, String to, String periodType) {
	public static void findPrice_Sector(MockHttpServletRequestBuilder request) {
		request.param("sectorCd", "0340");
		// week, month, p6month
		// request.param("intervalType", "week");
	}

	public static void findPrice_StockSector(MockHttpServletRequestBuilder request) {
		request.param("stockCd", "500112");
		request.param("fromDate", "2019-05-12");
		request.param("toDate", "2019-07-12");

	}

	public static void listPrice_Sector(MockHttpServletRequestBuilder request) {
		request.param("sectorCds", "0340", "0069");
		request.param("fromDate", "2019-05-12");
		request.param("toDate", "2019-06-12");
		// week, month, p6month
		// request.param("intervalType", "week");
	}

}
