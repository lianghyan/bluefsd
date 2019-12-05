package bet.bluefsd.exchange;

import java.util.Map;
import java.util.Random;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class ExchangeManager {
	static Random r = new Random(1);
	static int ui = 1;

	public static void create1(Map<String, Object> map) {
		ui = r.nextInt();
		map.put("exchCd", "shanghi" + ui);
		map.put("exchName", "BSE stock exchange" + ui);
		map.put("brief", "established in 1987" + ui);
		map.put("contact_addr", "Manama, Bahrain" + ui);
		map.put("remarks", "I have no more information" + ui);
	}

	public static void create2(Map<String, Object> map) {
		int ui = r.nextInt();
		map.put("exchCd", "dalian" + ui);
		map.put("exchName", "National Stock Exchange" + ui);
		map.put("brief", "The largest stock exchange in India" + ui);
		map.put("contact_addr", "Manama, Bahrain" + ui);
		map.put("remarks", "will get more information" + ui);

	}

	public static void update(Map<String, Object> map) {
		map.put("exchCd", "shanghi" + ui);
		map.put("exchName", "National Stock Exchange");
		map.put("brief", "The largest stock exchange in India");
		map.put("contact_addr", "Manama, Bahrain");
		map.put("remarks", "update with no more information");
	}
}
