package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ExampleApplication.class, args);
	}
	
	
	
	
	
	/**
	 * 额外推荐
	 * spring5.0 推荐使用的http工具
	 * 支持异步调用
	 * 组合查询
	 */
	@Bean
	public WebClient webClient() {
		
		// WebClient 构建方式1：
		WebClient.builder().build();
		WebClient.builder()
				 .baseUrl("baseUrl")
				 .defaultCookie("cookieKey", "value")
				 .defaultHeader("header", "value")
				 .build();
		// WebClient 构建方式2：
		WebClient.create("http://localhost:8080");
		return WebClient.create();
	}

}
