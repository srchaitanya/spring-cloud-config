package com.src.scgw;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdviceClass implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>> " + request.getHeader("jwtkey"));
		if(request.getHeader("jwtkey")==null && !request.getServletPath().endsWith("/getjwt")) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
