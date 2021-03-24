package com.ecummins.message.sender;

import com.ecummins.message.record.MessageRecord;
import com.ecummins.message.userinfo.UserInfo;

public abstract class AbstractSender implements Sender {

	private MessageHandler messageHandler;

	public AbstractSender(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	@Override
	public void send(UserInfo to, MessageRecord messageRecord, Object... objects) {
		SendResult sendResult = doSend(to, messageRecord, objects);
		messageRecord.setSendStatus(sendResult.isSuccess());
		messageRecord.setRamark(sendResult.getDescription());
	}

	protected abstract SendResult doSend(UserInfo to, MessageRecord messageRecord, Object... objects);

	public MessageHandler getMessageHandler() {
		return messageHandler;
	}

}
