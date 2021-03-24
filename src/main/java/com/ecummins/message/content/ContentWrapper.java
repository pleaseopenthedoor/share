package com.ecummins.message.content;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ecummins.message.exception.MessageParametersException;
import com.ecummins.message.type.SendType;

public class ContentWrapper {

	private Map<SendType, String> contentMap = new HashMap<>();

	
	
	/**
	 * 不同的发送类型对应不相同的发送内容
	 * 特别说明：
	 * 		1.如果sendTypes的数量少于contents，那么contents多余出来的的content会被废弃
	 * 		2.如果sendTypes的数量多于contents，那么sendTypes多余的部分默认按contents最后一个content
	 * 		3.如果sendTypes的数量等于contents，那么sendType与content默认按一对一
	 * 
	 * sendTypes ==>  s1,s2,s3   contents==> c1,c2,c3,c4
	 *                 |  |  |________________|__|__| 
	 *                 |  |___________________|__| 
	 *                 |______________________|   
	 *                 
	 * sendTypes ==>  s1,s2,s3   contents==> c1,c2
	 *                 |  |  |________________|__|
	 *                 |  |___________________|__| 
	 *                 |______________________|   
	 * @param content    消息内容
	 * @param sendTypes  发送类型
	 */
	protected ContentWrapper(String[] contents, SendType[] sendTypes) {
		if (null == sendTypes || sendTypes.length == 0 || null == contents || contents.length == 0 ) {
			throw new MessageParametersException("sendTypes or contents must not be null or empty");
		}
		
		if(sendTypes.length > contents.length) {
			for(int i = 0; i<sendTypes.length; i++) {
				if(i < contents.length) {
					contentMap.put(sendTypes[i], contents[i]);
				}else {
					contentMap.put(sendTypes[i], contents[contents.length]);
				}
			}
		}else {
			for(int i = 0; i<sendTypes.length; i++) {
				contentMap.put(sendTypes[i], contents[i]);
			}
		}
	}


	/**
	 * 获取用户包含的所有推送方式
	 */
	public Set<SendType> getSendTypeAll() {
		return contentMap.keySet();
	}
	
	
	/**
	 * 根据推送类型获取推送类型对应的消息内容
	 * 注：不同的推送类型可能对应不同的消息内容
	 */
	public String getContentBySendType(SendType sendType) {
		return contentMap.get(sendType);
	}
	
}
