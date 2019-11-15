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
		map.put("ret", "success");
		map.put("retMsg", msg);
		return map;
	}
}
