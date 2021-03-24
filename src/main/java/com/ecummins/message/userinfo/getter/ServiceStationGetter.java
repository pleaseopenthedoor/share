package com.ecummins.message.userinfo.getter;

import java.util.List;

import com.ecummins.message.type.IdType;
import com.ecummins.message.userinfo.UserInfo;


/**
 * 单例
 * 根据服务站id获取站长/信息员的用户信息
 */
public class ServiceStationGetter extends UserInfoGetter<UserInfo> {

	
	public ServiceStationGetter(UserInfoHandler<UserInfo> userInfoHandler) {
		super(userInfoHandler);
	}
	
	@Override
	public List<UserInfo> getUserInfoToList(List<Long> list) {
		return getUserInfoHandler().doUserInfoHandle(list);
	}

	@Override
	public boolean supportIdType(IdType idType) {
		return IdType.SERVICE_STATION_ID == idType;
	}
}
