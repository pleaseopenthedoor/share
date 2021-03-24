package com.example.exception.advice;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.response.BaseResponse;

/**
 * Controller异常拦截器
 * @author 52702
 *
 */
@RestControllerAdvice
public class AuthorizationExceptionAdvice {
	
	
	/**
	 * 自定义异常拦截
	 */
	@ExceptionHandler(org.apache.shiro.authz.AuthorizationException.class)
	public BaseResponse<String> AuthorizationExceptionHandle(AuthorizationException ex) {
		return new BaseResponse<String>(-200, ex.getMessage(), null);
	}
}
