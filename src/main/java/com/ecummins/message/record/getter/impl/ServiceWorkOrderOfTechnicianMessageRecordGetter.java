package com.ecummins.message.record.getter.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ecummins.message.content.ContentWrapper;
import com.ecummins.message.record.MessageRecordWrapper;
import com.ecummins.message.record.getter.MessageRecordGetter;
import com.ecummins.message.type.IdType;
import com.ecummins.message.userinfo.UserInfo;

public class ServiceWorkOrderOfTechnicianMessageRecordGetter implements MessageRecordGetter {

	@Override
	public boolean supportIdType(IdType idType) {
		return idType == IdType.SERVICE_WORK_ORDER_ID_PRINCIPAL_TECHNICIAN;
	}

	@Override
	public List<MessageRecordWrapper> doGetMessageRecordWrapper(List<UserInfo> userInfoList, Set<Long> idSet,
			ContentWrapper contentWrapper, IdType idType) {
		if (null != userInfoList && !userInfoList.isEmpty()) {
			return userInfoList.stream()
								.filter(u -> idSet.contains(u.getServiceWorkOrderIdOfPrincipalTechnician()))
								.map(u -> new MessageRecordWrapper(u, contentWrapper))
								.collect(Collectors.toList());
		}
		return null;
	}

}
