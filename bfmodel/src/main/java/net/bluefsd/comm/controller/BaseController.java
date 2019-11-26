package net.bluefsd.comm.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class BaseController {

	@Autowired
	protected MessageSource messageSource;

	public Map composeReturnMap() {
		Map<String, Object> map = new HashMap<>();
		// Locale locale = LocaleContextHolder.getLocale();
		Locale locale = Locale.CHINA;
		String msg = messageSource.getMessage("success", null, "success", locale);
		map.put("status", "0");
		map.put("retMsg", msg);
		return map;
	}

	public Map composeReturnMap(String msg) {
		Map<String, Object> map = new HashMap<>();
		// Locale locale = LocaleContextHolder.getLocale();
		map.put("status", "0");
		map.put("retMsg", msg);
		return map;
	}

	public Map composeReturnMap(String key, Object object) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", "0");
		map.put(key, object);
		map.put("retMsg", "success");
		return map;
	}

	public Map composeErrorMap(String msg) {
		Map<String, Object> map = new HashMap<>();
		// Locale locale = LocaleContextHolder.getLocale();
		// Locale locale = Locale.CHINA;
		// String msg = messageSource.getMessage("success", null, "success", locale);
		map.put("status", "-1");
		map.put("retMsg", msg);
		return map;
	}
}
