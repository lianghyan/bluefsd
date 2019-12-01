package net.bluefsd.cmm;

import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		// http://localhost:8769/auth/login
		System.out.println("------------------getRequestURL:" + request.getRequestURL().toString());
		System.out.println("------------------getContextPath:" + request.getContextPath());
		System.out.println("------------------getServletPath:" + request.getServletPath());
		Enumeration e=request.getParameterNames();
		while(e.hasMoreElements()) {
			System.out.println("------------------"+e.nextElement());
		}
		Object accessToken = request.getParameter("token");
//		InputStream in=request.getInputStream();
//		System.out.println(in.available());
//		byte[] b=new byte[in.available()];
//		in.read(b);
//		System.out.print(b.toString());
//		String hToken = request.getHeader("Authorization");
//		if (accessToken == null) {
//			return false;
//		}
	
		return true;
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