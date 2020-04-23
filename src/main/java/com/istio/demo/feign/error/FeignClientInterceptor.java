package com.istio.demo.feign.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String TOKEN_TYPE = "Bearer";

	/*
	 * @Override public void apply(RequestTemplate requestTemplate) { Authentication
	 * authentication = SecurityContextHolder.getContext().getAuthentication();
	 * 
	 * if (authentication != null && authentication.getDetails() instanceof
	 * OAuth2AuthenticationDetails) { OAuth2AuthenticationDetails details =
	 * (OAuth2AuthenticationDetails) authentication.getDetails();
	 * requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s",
	 * TOKEN_TYPE, details.getTokenValue())); } }
	 */

	@Override
	public void apply(RequestTemplate template) {
		if (((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()) != null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			System.out.println("request.getHeader(HttpHeaders.AUTHORIZATION)" + request.getHeader(HttpHeaders.AUTHORIZATION));
			System.out.println("template "+ template.toString());
			template.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
		}
	}
}