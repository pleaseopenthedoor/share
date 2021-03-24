package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.response.BaseResponse;

@RequestMapping("app")
public class AppController {
	
	
	@GetMapping("appIndex")
	public BaseResponse<String> appIndex(){
		return new BaseResponse<String>("欢迎来到王者农药");
	}
}
