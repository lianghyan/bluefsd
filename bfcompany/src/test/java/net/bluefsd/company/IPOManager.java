package net.bluefsd.company;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class IPOManager {
	public static void create_1(Map<String, Object> map) {
		map.put("companyCd", "01NEL");
		map.put("exchCd", "NSE");
		map.put("pricePerShare", "10.00");
		map.put("totalShares", "1000");
		map.put("openDate", "2020-02-02 09:00:00");
		map.put("remarks", "good luck");
	}

	public static void create_2(Map<String, Object> map) {
		map.put("companyCd", "01YXL");
		map.put("exchCd", "NSE");
		map.put("pricePerShare", "10.00");
		map.put("totalShares", "1000");
		map.put("openDate", "2020-02-02 09:00:00");
		map.put("remarks", "good luck");
	}

	private static Timestamp getTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date timeDate = null;
		try {
			timeDate = dateFormat.parse("2020-02-02 09:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
		return dateTime;
	}

}
