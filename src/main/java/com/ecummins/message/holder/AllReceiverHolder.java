package com.ecummins.message.holder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.ecummins.message.content.ContentWrapper;
import com.ecummins.message.type.IdType;

/**
 * 多角色消息推送容器
 * @author 52702
 *
 */
public class AllReceiverHolder extends AbstractReceiverHolder {
	
	
	public AllReceiverHolder(ContentWrapper contentWrapper, IdType idType, Long...ids) {
		addMessageWrapper(contentWrapper, idType, ids);
	}
	
	/**
	 * 添加消息
	 * @param idSet
	 * @param idType
	 * @param contentWrapper
	 * @return
	 */
	public AllReceiverHolder addMessageWrapper(ContentWrapper contentWrapper, IdType idType, Long...ids) {
		Set<Long> set = new HashSet<>(Arrays.asList(ids));
		set.remove(null);
		doAddMessageWrapper(set, idType, contentWrapper);
		return this;
	}
}
