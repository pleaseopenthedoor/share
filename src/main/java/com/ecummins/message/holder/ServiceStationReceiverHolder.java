package com.ecummins.message.holder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.ecummins.message.content.ContentWrapper;
import com.ecummins.message.type.IdType;

/**
 * 站长/信息员消息推送容器
 * @author 52702
 *
 */
public class ServiceStationReceiverHolder extends AbstractReceiverHolder {
	
	
	public ServiceStationReceiverHolder(ContentWrapper contentWrapper, Long...ids) {
		addMessageWrapper(contentWrapper, ids);
	}
	
	
	/**
	 * 添加消息
	 * @param idSet
	 * @param idType
	 * @param contentWrapper
	 * @return
	 */
	public ServiceStationReceiverHolder addMessageWrapper(ContentWrapper contentWrapper, Long...ids) {
		Set<Long> set = new HashSet<>(Arrays.asList(ids));
		set.remove(null);
		doAddMessageWrapper(set, IdType.SERVICE_STATION_ID, contentWrapper);
		return this;
	}
	
}
