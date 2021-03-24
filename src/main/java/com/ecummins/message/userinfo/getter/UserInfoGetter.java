package com.ecummins.message.userinfo.getter;

import java.util.List;

import com.ecummins.message.type.SupportIdType;
import com.ecummins.message.userinfo.UserInfo;

public abstract class UserInfoGetter<T> implements SupportIdType {
	
	private UserInfoHandler<UserInfo> userInfoHandler;
	
	
	public UserInfoGetter(UserInfoHandler<UserInfo> userInfoHandler) {
		this.userInfoHandler = userInfoHandler;
	}
	
	public UserInfoHandler<UserInfo> getUserInfoHandler() {
		return userInfoHandler;
	}


	public abstract List<T> getUserInfoToList(List<Long> list);
	
	
	@FunctionalInterface
	public interface UserInfoHandler<T>{
		
		List<T> doUserInfoHandle(List<Long> idList);
	}
	
}
