package com.aayush.webServices;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

	
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Override
	public boolean shouldFilter() {
		// do u need to use a filter?
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// logging contents of the request that ZUUL intercepts
		//we can implement security and so much more here
		
		//get the request content
		HttpServletRequest request=RequestContext.getCurrentContext().getRequest();
		logger.info("request->{} request uri->{}",request,request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		// when should the filter happen (before request or after or only exception?)
		return "pre";
	}

	@Override
	public int filterOrder() {
		//set up proprity for zuul filters
		
		return 1;
	}

	
}
