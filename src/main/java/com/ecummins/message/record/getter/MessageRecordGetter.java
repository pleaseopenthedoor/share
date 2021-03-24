package com.ecummins.message.record.getter;

import java.util.List;
import java.util.Set;

import com.ecummins.message.content.ContentWrapper;
import com.ecummins.message.record.MessageRecordWrapper;
import com.ecummins.message.type.IdType;
import com.ecummins.message.type.SupportIdType;
import com.ecummins.message.userinfo.UserInfo;

public interface MessageRecordGetter extends SupportIdType{
	
	List<MessageRecordWrapper> doGetMessageRecordWrapper(List<UserInfo> userInfoList, Set<Long> idSet,	ContentWrapper contentWrapper, IdType idType);
}
