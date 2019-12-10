package net.bluefsd.security;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Autowired
	TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		// http://localhost:8769/auth/login
		System.out.println("getRequestURL:" + request.getRequestURL().toString());
		System.out.println("getContextPath:" + request.getContextPath());
		System.out.println("getServletPath:" + request.getServletPath());

		Enumeration names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			System.out.println("--header----" + name + ":" + request.getHeader(name));
		}
		names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			System.out.println("--param----" + name + ":" + request.getParameter(name));
		}
		String method=request.getMethod();
		if("OPTIONS".equals(method)) {
			response.setStatus(200);
			response.getWriter().write("ok");
			return true;
		}
		String fsdtoken = request.getHeader("fsdtoken");
		if (fsdtoken == null) {
			fsdtoken = request.getParameter("fsdtoken");
		}
		String allow = tokenService.allowaccess();
		if("ok".equals(allow)) 
		{
			return true;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {

	}
}