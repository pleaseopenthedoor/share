package com.example.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.User;
import com.example.response.BaseResponse;
import com.example.utils.HttpUtils;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	private String secret = "我是秘钥";
	
	/**
	 * 用户登录
	 */
	@PostMapping("/login")
	public BaseResponse<String> login(@RequestBody User user){
		
		String userJson = JSON.toJSONString(new User(1L, user.getUsername(), "22222222222"));
		
		String token =  JWT.create()
							.withExpiresAt(new Date())
							.withClaim("user", userJson)
							.sign(Algorithm.HMAC256(this.secret));
		
		return new BaseResponse<String>(token);
	}
	
	/**
	 * 无需认证
	 */
	@GetMapping("/notAuth")
	public BaseResponse<String> notAuth(){
		return new BaseResponse<String>("无需认证，请求成功");
	}
	
	/**
	 * 只需要认证
	 */
	@GetMapping("/auth")
	public BaseResponse<String> auth(){
		return new BaseResponse<String>("需认证，请求成功");
	}
	
	
	/**
	 * 需认证且需要某种角色
	 */
	@GetMapping("/users")
	// 注：角色是并且关系，必须同时满足
	// @RequiresRoles(value = {"管理员", "总经理"})
	@RequiresRoles(value = {"管理员"})
	public BaseResponse<List<User>> users(){
		List<User> users = Arrays.asList(new User(1L, "带有管理员角色", "1111111111"));
		return new BaseResponse<List<User>>(users);
	}
	
	
	/**
	 * 需认证且需要对应的权限
	 */
	@GetMapping("/user")
	// 注：权限是并且关系，必须同时满足
	//@RequiresPermissions(value = {"权限A","权限B"})
	@RequiresPermissions(value = {"权限A"})
	public BaseResponse<User> user(){
		User currentUser = HttpUtils.getCurrentUser();
		return new BaseResponse<User>(currentUser);
	}
	
}
