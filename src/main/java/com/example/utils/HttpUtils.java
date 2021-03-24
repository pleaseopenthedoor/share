package com.example.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.entity.User;

public class HttpUtils {
	
	private static final String USER_ATTRIBUTE = "userAttribute";
	
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	
	private static void addParamToRequest(String key, Object value) {
		getHttpServletRequest().setAttribute(key, value);
	}
	
	public static void addUserToRequest(User user) {
		addParamToRequest(USER_ATTRIBUTE, user);
	}
	
	
	public static User getCurrentUser() {
		return (User)getHttpServletRequest().getAttribute(USER_ATTRIBUTE);
	}
	
}
