package com.ecummins.message.userinfo.getter;

import java.util.List;

import com.ecummins.message.type.IdType;
import com.ecummins.message.userinfo.UserInfo;

/**
 * 单例
 * 根据userId获取用户信息
 */
public class UserGetter extends UserInfoGetter<UserInfo> {

	public UserGetter(UserInfoHandler<UserInfo> userInfoHandler) {
		super(userInfoHandler);
	}

	@Override
	public List<UserInfo> getUserInfoToList(List<Long> list) {
		return getUserInfoHandler().doUserInfoHandle(list);
	}

	@Override
	public boolean supportIdType(IdType idType) {
		return IdType.USER_ID == idType;
	}

}
