package com.example.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.User;
import com.example.shiro.token.JwtToken;
import com.example.utils.HttpUtils;

@Component
public class JwtTokenRealm extends AuthorizingRealm{
	
	private String secret = "我是秘钥";

	
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}
	
	/**
	 * 获取用户的角色以及权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 用户角色
		Set<String> roles = new HashSet<>();
		//roles.add("管理员");
		//roles.add("总经理");
		
		// 用户权限
		Set<String> permissions = new HashSet<>();
		permissions.add("权限A");
		permissions.add("权限B");
		
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setRoles(roles);
		simpleAuthorizationInfo.setStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		
		String token = (String)authenticationToken.getPrincipal();
		
		// 校验token是否有效
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(this.secret))
									// 设置token的过期时长 单位：秒
									.acceptExpiresAt(30 * 60)
									.build();

		try {
			DecodedJWT verify = verifier.verify(token);
			
			// 将用户信息放入请求中
			Claim claim = verify.getClaim("user");
			String userJson = claim.asString();
			HttpUtils.addUserToRequest(JSON.parseObject(userJson, User.class));
		} catch (TokenExpiredException e) {
			throw new AuthenticationException("token过期了");
		}catch (InvalidClaimException e) {
			throw new AuthenticationException("claim错误");
		}catch (Exception e) {
			throw new AuthenticationException(e.getMessage());
		}
		
		
		/**
		 * 根据token可以查询数据库或Redis校验当前token是否有效，若无效抛出异常
		 */
		return new SimpleAuthenticationInfo(authenticationToken, authenticationToken.getCredentials(), getName());
	}

}
