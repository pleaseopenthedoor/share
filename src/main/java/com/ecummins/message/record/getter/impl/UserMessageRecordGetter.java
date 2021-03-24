package com.ecummins.message.record.getter.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ecummins.message.content.ContentWrapper;
import com.ecummins.message.record.MessageRecordWrapper;
import com.ecummins.message.record.getter.MessageRecordGetter;
import com.ecummins.message.type.IdType;
import com.ecummins.message.userinfo.UserInfo;

/**
 * 单例
 */
public class UserMessageRecordGetter implements MessageRecordGetter {

	/**
	 * 根据UserId从userInfoList集合中过滤出idSet中包含的UserInfo
	 * 生成MessageRecordWrapper
	 */
	@Override
	public List<MessageRecordWrapper> doGetMessageRecordWrapper(List<UserInfo> userInfoList, Set<Long> idSet, ContentWrapper contentWrapper, IdType idType) {

		if (null != userInfoList && !userInfoList.isEmpty()) {
			return userInfoList.stream().filter(u -> idSet.contains(u.getUserId()))
					.map(u -> new MessageRecordWrapper(u, contentWrapper)).collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public boolean supportIdType(IdType idType) {
		return IdType.USER_ID == idType;
	}

}
