package com.ecummins.message.userinfo.getter;

import java.util.List;

import com.ecummins.message.type.IdType;
import com.ecummins.message.userinfo.UserInfo;

public class PrincipalTechnicianGetter extends UserInfoGetter<UserInfo> {

	public PrincipalTechnicianGetter(UserInfoHandler<UserInfo> userInfoHandler) {
		super(userInfoHandler);
	}

	@Override
	public boolean supportIdType(IdType idType) {
		
		return idType == IdType.SERVICE_WORK_ORDER_ID_PRINCIPAL_TECHNICIAN;
	}

	@Override
	public List<UserInfo> getUserInfoToList(List<Long> list) {
		return getUserInfoHandler().doUserInfoHandle(list);
	}

}
