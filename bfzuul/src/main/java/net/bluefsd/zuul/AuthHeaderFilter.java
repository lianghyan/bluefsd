package net.bluefsd.zuul;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
@Component
public class AuthHeaderFilter extends ZuulFilter {
	public final static String HEADER_AUTH = "Authorization";

	public AuthHeaderFilter() {
		super();
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 5;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token=request.getHeader(HEADER_AUTH);
 		ctx.addZuulRequestHeader("original_requestURL",request.getRequestURL().toString());		 
		ctx.addZuulRequestHeader(HEADER_AUTH,token);
		ctx.addZuulRequestHeader("FSDAuth",token);
 		return null;
	}
}