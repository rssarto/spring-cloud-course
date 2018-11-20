package com.in28minutes.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter {
	
	/*
	 * The URL below identifies the way requests are sent through api gateway
	 * http://localhost:8765/{application-name}/{uri}
	 * Example: 
	 * http://localhost:8765/currency-conversion-service/currency-converter-feign/from/EUR/to/INR/quantity/10000
	 */
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		/* Indicates if this filter should be executed. */
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		/* Where we define the interception logic */
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("request -> {} request uri -> {}", request, request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		/*
		 * Indicates when should the filtering be applyed.
		 * "pre"   - before the request is executed
		 * "post"  - after the request is executed
		 * "error" - only request that presents error 
		 */
		return "pre";
	}

	@Override
	public int filterOrder() {
		/*
		 * Indicates the priority order to the filter
		 */
		return 1;
	}

}
