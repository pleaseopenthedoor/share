package com.ecummins.message.holder.receiver;

import java.util.Set;

import com.ecummins.message.exception.MessageParametersException;
import com.ecummins.message.type.IdType;

public abstract class AbstractReceiver {

	/**
	 * 接收人id
	 */
	private Set<Long> idSet;
	
	/**
	 * id类型 默认服务站id
	 * {@link IdType}
	 */
	private IdType idType = IdType.SERVICE_STATION_ID;
	
	

	public AbstractReceiver(Set<Long> idSet, IdType idType) {
		super();
		
		if(idSet == null || idSet.isEmpty()) {
			throw new MessageParametersException("idSet must not be null or empty");
		}
		
		if(idType == null) {
			throw new MessageParametersException("idType must not be null");
		}
		
		this.idSet = idSet;
		this.idType = idType;
	}
	

	public Set<Long> getIdSet() {
		return idSet;
	}

	public IdType getIdType() {
		return idType;
	}

}
