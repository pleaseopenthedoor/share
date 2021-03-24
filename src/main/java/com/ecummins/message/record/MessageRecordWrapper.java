package com.ecummins.message.record;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ecummins.message.content.ContentWrapper;
import com.ecummins.message.type.SendType;
import com.ecummins.message.userinfo.UserInfo;

/**
 * 消息记录包装类
 */
public class MessageRecordWrapper {
	
	/**
	 * 消息接收人的信息
	 */
	private UserInfo userInfo;

	/**
	 * 消息发送方式以及对应的消息内容
	 */
	private ContentWrapper contentWrapper;

	/**
	 * 消息记录
	 */
	private List<MessageRecord> messageRecordList;

	public MessageRecordWrapper(UserInfo userInfo, ContentWrapper contentWrapper) {
		super();
		this.userInfo = userInfo;
		this.contentWrapper = contentWrapper;

		this.messageRecordList = contentWrapper.getSendTypeAll()
				.stream()
				.map(st -> {
						return new MessageRecord(userInfo.getUserId(), contentWrapper.getContentBySendType(st), st.getCode());
					})
				.collect(Collectors.toList());
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public Set<SendType> getSendTypeAll() {
			return contentWrapper.getSendTypeAll();
	}

	public List<MessageRecord> getMessageRecordList() {
		return messageRecordList;
	}
}
