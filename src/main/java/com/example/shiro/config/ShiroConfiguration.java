package com.example.shiro.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.shiro.filter.JwtTokenFilter;
import com.example.shiro.realm.JwtTokenRealm;

@Configuration
public class ShiroConfiguration {
	
	@Bean
	public SecurityManager securityManager(JwtTokenRealm jwtTokenRealm) {
		// 单例全局唯一
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(jwtTokenRealm);
		return defaultWebSecurityManager;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		Map<String, Filter> filters = new HashMap<>();
		// 设置过滤器的别名，拦截请求某些请求时需要指定别名
		filters.put("jwtTokenFilter", new JwtTokenFilter());
		// 其它过滤器
		//filters.put("", new JwtTokenFilter());
		
		// 设置过滤器链
		shiroFilterFactoryBean.setFilters(filters);
		
		Map<String, String> filterChainDefinitionMap = new HashMap<>();
		filterChainDefinitionMap.put("/user/login", "anon");
		filterChainDefinitionMap.put("/user/notAuth", "anon");
		filterChainDefinitionMap.put("/user/auth", "jwtTokenFilter");
		filterChainDefinitionMap.put("/user/users", "jwtTokenFilter");
		filterChainDefinitionMap.put("/user/user", "jwtTokenFilter");
		filterChainDefinitionMap.put("/**", "jwtTokenFilter");
		
		// 设置过滤器与拦截请求的对应关系
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap );
		
		return shiroFilterFactoryBean;
	}
	

    /**
     * 管理shiro一些Bean的生命周期
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     *  校验用户是否有权限访问接口
     *  在接口方法上加入注解：
     *  @RequiresRoles("R0041")
     *  @RequiresPermissions("shiro:user:user")
     *  依赖包:
     *      <dependency>
	 *			<groupId>org.springframework.boot</groupId>
	 *			<artifactId>spring-boot-starter-aop</artifactId>
	 *		</dependency>
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
