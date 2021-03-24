package com.example.shiro.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.example.response.BaseResponse;
import com.example.shiro.token.JwtToken;

public class JwtTokenFilter extends AuthenticatingFilter {
	
	
	/**
	 * {@link AuthenticatingFilter.executeLogin 回调当前方法拿到Token}
	 */
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String token = getTokenFromRequest((HttpServletRequest) request);
		return new JwtToken(token);
	}
	
	
	/**
	 * 
	 * 首次执行的方法
	 * 
	 * 当前请求是否允许访问
	 * true：表示允许访问，直接放行
	 * false：表示不允许访问，进入{@link #onAccessDenied}
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		// OPTIONS 请求为浏览器的预检请求 (解决 CORS(cross-origin resource sharing)跨域资源共享)
		if(((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())){
			return true;
		}
		return false;
	}

	
	/**
	 * {@link #isAccessAllowed}返回false之后，进入当前方法
	 * 访问被拒绝后调用此方法
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		String token = getTokenFromRequest((HttpServletRequest) request);
		
		// 如果请求中未携带token直接返回错误信息
		if(Objects.isNull(token)) {
			return onLoginFailure(null, new AuthenticationException("token不能为空"), request, response);
		}
		
		// 进入验证逻辑
		return executeLogin(request, response);
	}
	
	/**
	 * 认证失败时回调此方法
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setContentType("application/json;charset=utf-8");
        try {
        	BaseResponse<String> baseResponse = new BaseResponse<String>(-100, e.getMessage(), null);
            httpResponse.getWriter().print(JSON.toJSONString(baseResponse));
        } catch (IOException e1) {
        	
        }
        return false;
	}
	
	
	
	private String getTokenFromRequest(HttpServletRequest request) {
		return request.getHeader("token");
	}
}
