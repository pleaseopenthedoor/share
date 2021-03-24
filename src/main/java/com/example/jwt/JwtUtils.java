package com.example.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;

public class JwtUtils {
	
		
	public static void main(String[] args) {
		
		
		String token =  JWT.create().withKeyId("kid")
						.withExpiresAt(new Date())
						.withClaim("username", "zhangsan!")
						.sign(Algorithm.HMAC256("secret"));
					
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret"))
							.withClaim("username", "zhangsan")
							.acceptExpiresAt(3)
							.build();
		
		try {
			verifier.verify(token);
		} catch (TokenExpiredException e) {
			System.out.println("token was expire");
		}catch (InvalidClaimException e) {
			System.out.println("参数Claim异常");
		}catch (Exception e) {
			System.out.println(e.getClass());
		}
		
	}
	
}
