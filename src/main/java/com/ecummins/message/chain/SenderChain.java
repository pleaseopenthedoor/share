package com.ecummins.message.chain;

import java.util.List;
import java.util.Objects;

import com.ecummins.message.exception.MessageParametersException;
import com.ecummins.message.record.MessageRecord;
import com.ecummins.message.record.MessageRecordWrapper;
import com.ecummins.message.sender.Sender;
import com.ecummins.message.userinfo.UserInfo;

/**
 * 发送器链
 */
public class SenderChain {
	
	/**
	 * 首节点
	 */
	private SenderNode headerNode;
	
	/**
	 * 尾节点
	 */
	private SenderNode taillNode;
	
	private SenderChain(Sender sender) {
		if(sender == null) {
			throw new MessageParametersException("sender must not be null");
		}
		headerNode = new SenderNode(sender);
	}
	
	public static SenderChain initSenderChain(Sender sender) {
		return new SenderChain(sender);
		
	}
	
	/**
	 * 追加Node节点 
	 */
	public SenderChain addSenderNode(Sender sender) {
		SenderNode newTaillNode = new SenderNode(sender);
		if(Objects.isNull(this.taillNode)) {
			this.taillNode = newTaillNode;
			this.headerNode.setNextNode(this.taillNode);
		}else {
			this.taillNode.setNextNode(newTaillNode);
			this.taillNode = newTaillNode;
		}
		return this;
	}
	
	public void handleMessageRecord(MessageRecordWrapper messageRecordWrapper) {
		List<MessageRecord> messageRecordList = messageRecordWrapper.getMessageRecordList();
		messageRecordList.forEach(record -> headerNode.handle(messageRecordWrapper.getUserInfo(),record));
	}
	
	
	private class SenderNode{
		
		/**
		 * 消息发送器
		 */
		private Sender sender;
		
		/**
		 * 下一个节点
		 */
		private SenderNode nextNode;
		

		private SenderNode(Sender sender) {
			this.sender = sender;
		}
		
		public void setNextNode(SenderNode node) {
			nextNode = node;
		}

		public void handle(UserInfo to, MessageRecord messageRecord) {
			if(messageRecord.getSendType() == this.sender.sendType().getCode()) {
				sender.send(to, messageRecord);
			}else {
				if(!Objects.isNull(this.nextNode)) {
					this.nextNode.handle(to, messageRecord);
				}
			}
			
		}
	}
}
