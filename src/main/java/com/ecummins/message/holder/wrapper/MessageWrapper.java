package com.ecummins.message.holder.wrapper;

import java.util.Set;

import com.ecummins.message.content.ContentWrapper;
import com.ecummins.message.holder.receiver.AbstractReceiver;
import com.ecummins.message.type.IdType;

/**
 *  非单例
 *	消息包装类 
 *	包含：
 *		id集合
 *		消息内容
 *		id类型(服务站id/userId){@link IdType}
 */
public class MessageWrapper extends AbstractReceiver{

	
	private ContentWrapper contentWrapper;
	
	
	public MessageWrapper(Set<Long> idSet, IdType idType, ContentWrapper contentWrapper) {
		super(idSet, idType);
		this.contentWrapper = contentWrapper;
	}

	public ContentWrapper getContentWrapper() {
		return contentWrapper;
	}

}
