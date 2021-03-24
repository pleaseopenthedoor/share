package com.ecummins.message.holder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.ecummins.message.content.ContentWrapper;
import com.ecummins.message.type.IdType;

/**
 * User消息推送容器
 * @author 52702
 *
 */
public  class UserReceiverHolder extends AbstractReceiverHolder {
	
	
	public UserReceiverHolder(ContentWrapper contentWrapper, Long...ids) {
		addMessageWrapper(contentWrapper, ids);
	}
	
	
	/**
	 * 添加消息
	 * @param idSet
	 * @param idType
	 * @param contentWrapper
	 * @return
	 */
	public UserReceiverHolder addMessageWrapper(ContentWrapper contentWrapper, Long...ids) {
		Set<Long> set = new HashSet<>(Arrays.asList(ids));
		set.remove(null);
		doAddMessageWrapper(set, IdType.USER_ID, contentWrapper);
		return this;
	}
}
