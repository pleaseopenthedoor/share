package com.ecummins.message.record;

/**
 * 消息记录
 */
public class MessageRecord {
	
	/**
	 * 接收人的id
	 */
	private Long receiverId;
	
	
	/**
	 * 发送内容
	 */
	private String content;
	
	
	/**
	 * 发送状态
	 */
	private boolean sendStatus;
	
	/**
	 * 发送类型
	 */
	private int sendType;
	
	/**
	 * 发送结果备注
	 */
	private String ramark;

	
	
	
	
	public MessageRecord(Long receiverId, String content, int sendType) {
		super();
		this.receiverId = receiverId;
		this.content = content;
		this.sendType = sendType;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public boolean isSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(boolean sendStatus) {
		this.sendStatus = sendStatus;
	}

	public int getSendType() {
		return sendType;
	}

	public void setSendType(int sendType) {
		this.sendType = sendType;
	}

	public String getRamark() {
		return ramark;
	}

	public void setRamark(String ramark) {
		this.ramark = ramark;
	}

	@Override
	public String toString() {
		return "MessageRecord [receiverId=" + receiverId + ", content=" + content + ", sendStatus=" + sendStatus
				+ ", sendType=" + sendType + ", ramark=" + ramark + "]";
	}
	
}
