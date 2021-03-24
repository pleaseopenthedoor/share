package com.ecummins.message.holder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ecummins.message.content.ContentWrapper;
import com.ecummins.message.holder.wrapper.MessageWrapper;
import com.ecummins.message.type.IdType;

/**
 * 相当于一个消息接收者容器
 */
public abstract class AbstractReceiverHolder {

	/**
	 * 消息包装集合
	 */
	private List<MessageWrapper> messageWrapperList = new ArrayList<>();

	/**
	 * 按IdType划分出不同的Id集合
	 */
	private Map<IdType, Set<Long>> idTypeIdSetMap = new HashMap<>();
	
	
	protected void doAddMessageWrapper(Set<Long> idSet, IdType idType, ContentWrapper contentWrapper) {
		MessageWrapper messageWrapper = new MessageWrapper(idSet, idType, contentWrapper);
		messageWrapperList.add(messageWrapper);
		doIdTypeIdSetIntoMap(idSet, idType);
	}

	
	private void doIdTypeIdSetIntoMap(Set<Long> idSet, IdType idType) {
		if (this.idTypeIdSetMap.containsKey(idType)) {
			this.idTypeIdSetMap.get(idType).addAll(idSet);
		} else {
			this.idTypeIdSetMap.put(idType, new HashSet<>(idSet));
		}
	}

	
	public List<MessageWrapper> getMessageWrapperList() {
		return messageWrapperList;
	}

	public Set<Long> getIdSetByIdType(IdType idType){
		return this.idTypeIdSetMap.get(idType);
	}
	
}
