package com.ecummins.message.type;

/**
 * 发送类型 
 */
public enum SendType {

	/**
	 * 短信推送
	 */
	SMS(1),
	
	/**
	 * App推送
	 */
	APP(2),
	
	/**
	 * 邮箱推送
	 */
	EMAIL(3);

	private int code;

	SendType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
