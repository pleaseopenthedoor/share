package com.ecummins.message.sender;

import com.ecummins.message.record.MessageRecord;
import com.ecummins.message.type.SendType;
import com.ecummins.message.userinfo.UserInfo;

public interface Sender {

	/**
	 * 
	 * @param to 发送地址(手机号、邮箱、ClientId...)
	 * @param content 发送内容
	 * @return
	 */
	void send(UserInfo to, MessageRecord messageRecord, Object...objects);

	/**
	 * 发送类型 
	 */
	SendType sendType();

}
