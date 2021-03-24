package com.ecummins.message.userinfo;

import java.util.Map;

/**
 *  用户信息holder
 *	包含需要推送消息的用户信息以及推送的消息内容
 */
public class UserInfoHolder {

	/**
	 * 用户信息
	 */
	private Map<Long, UserInfo> userInfoMap;

	/**
	 * 消息内容
	 */
	private String content;

	public UserInfoHolder(Map<Long, UserInfo> userInfoMap, String content) {
		super();
		this.userInfoMap = userInfoMap;
		this.content = content;
	}

	public Map<Long, UserInfo> getUserInfoMap() {
		return userInfoMap;
	}

	public String getContent() {
		return content;
	}

}
