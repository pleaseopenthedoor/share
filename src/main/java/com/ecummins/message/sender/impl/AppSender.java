package com.ecummins.message.sender.impl;

import com.ecummins.message.record.MessageRecord;
import com.ecummins.message.sender.AbstractSender;
import com.ecummins.message.sender.MessageHandler;
import com.ecummins.message.sender.SendResult;
import com.ecummins.message.type.SendType;
import com.ecummins.message.userinfo.UserInfo;

public class AppSender extends AbstractSender {

	public AppSender(MessageHandler messageHandler) {
		super(messageHandler);
	}

	@Override
	protected SendResult doSend(UserInfo to, MessageRecord messageRecord, Object... objects) {
		return getMessageHandler().messageHandle(to.getClientId(), messageRecord.getContent());
	}

	@Override
	public SendType sendType() {
		return SendType.APP;
	}

}
