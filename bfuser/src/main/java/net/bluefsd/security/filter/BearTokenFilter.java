package net.bluefsd.security.filter;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;

@Component
public class BearTokenFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BearTokenUtil jwtTokenUtil;

	public static final String BEAR_TOKEN = "Bearer";
	public static final String HEADER_AUTH = "Authorization";
	public static String loginPath = "/auth/login";
	public static String logoutPath = "/auth/logout";
	public static String tokenPath = "/auth/allowaccess";

	private boolean needValidate(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		if (url.endsWith(loginPath) || url.endsWith(logoutPath)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		if (!needValidate(request)) {
			String reqHeader = request.getHeader(HEADER_AUTH);
			String msg = null;
			if (reqHeader == null) {
				msg = "Token is not provided";
				composeResponse(request, response, msg);
				return;
			}

			if (!reqHeader.startsWith(BEAR_TOKEN)) {
				msg = "Token type is not matched!";
				composeResponse(request, response, msg);
				return;
			}

			final String authToken = reqHeader.substring(BEAR_TOKEN.length());
			String username = null;
			try {
				username = jwtTokenUtil.getUserNameFromToken(authToken);
			} catch (Exception e) {
				msg = e.getMessage();
				composeResponse(request, response, msg);
				return;
			}

			if (StringUtils.isEmpty(username)) {
				msg = "Invalid token";
				composeResponse(request, response, msg);
				return;
			}
			boolean isValid = false;

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			try {
				isValid = jwtTokenUtil.validateToken(authToken, userDetails);
			} catch (Exception e) {
				msg = e.getMessage();
				composeResponse(request, response, msg);
				return;
			}
			if (isValid) {
				if (SecurityContextHolder.getContext().getAuthentication() == null) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		chain.doFilter(request, response);

	}

	private void composeResponse(HttpServletRequest request, HttpServletResponse response, String msg)
			throws ServletException, IOException {
		String url = request.getServletPath();
		if (url.endsWith(tokenPath)) {
			response.getWriter().write("false");
		} else {
			Date curDate = new Date();
			Map<String, String> map = new HashMap();
			map.put("timestamp", DateFormat.getInstance().format(curDate));
			map.put("error", msg);
			map.put("status", "403");
			map.put("path", request.getRequestURL().toString());
			response.getWriter().write(JSON.toJSONString(map));
		}
	}
}