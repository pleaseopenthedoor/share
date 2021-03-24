package com.ecummins.message.sender.impl;

import com.ecummins.message.record.MessageRecord;
import com.ecummins.message.sender.AbstractSender;
import com.ecummins.message.sender.MessageHandler;
import com.ecummins.message.sender.SendResult;
import com.ecummins.message.type.SendType;
import com.ecummins.message.userinfo.UserInfo;

public class EmailSender extends AbstractSender {

	public EmailSender(MessageHandler messageHandler) {
		super(messageHandler);
	}

	@Override
	protected SendResult doSend(UserInfo to, MessageRecord messageRecord, Object... objects) {
		return getMessageHandler().messageHandle(to.getEmail(), messageRecord.getContent(),"e路康明斯email提醒");
	}

	@Override
	public SendType sendType() {
		return SendType.EMAIL;
	}

}
