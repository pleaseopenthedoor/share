package com.ecummins.message.holder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.ecummins.message.content.ContentWrapper;
import com.ecummins.message.type.IdType;

/**
 * 主技师消息推送容器
 * @author 52702
 *
 */
public class PrincipalTechnicianReceiverHolder extends AbstractReceiverHolder {

	public PrincipalTechnicianReceiverHolder(ContentWrapper contentWrapper, Long...ids) {
		addMessageWrapper(contentWrapper, ids);
	}
	
	
	/**
	 * 添加消息
	 * @param idSet
	 * @param idType
	 * @param contentWrapper
	 * @return
	 */
	public PrincipalTechnicianReceiverHolder addMessageWrapper(ContentWrapper contentWrapper, Long...ids) {
		Set<Long> set = new HashSet<>(Arrays.asList(ids));
		set.remove(null);
		doAddMessageWrapper(set, IdType.SERVICE_WORK_ORDER_ID_PRINCIPAL_TECHNICIAN, contentWrapper);
		return this;
	}
}
