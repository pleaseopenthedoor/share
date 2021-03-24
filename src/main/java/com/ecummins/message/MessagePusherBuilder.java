package com.ecummins.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.ecummins.message.chain.SenderChain;
import com.ecummins.message.content.ContentWrapper;
import com.ecummins.message.exception.MessageParametersException;
import com.ecummins.message.holder.AbstractReceiverHolder;
import com.ecummins.message.holder.wrapper.MessageWrapper;
import com.ecummins.message.persistence.DataPersistence;
import com.ecummins.message.record.MessageRecord;
import com.ecummins.message.record.MessageRecordWrapper;
import com.ecummins.message.record.getter.MessageRecordGetter;
import com.ecummins.message.type.IdType;
import com.ecummins.message.userinfo.UserInfo;
import com.ecummins.message.userinfo.getter.UserInfoGetter;


/**
 * 单例
 */
public class MessagePusherBuilder {

	/**
	 * 消息发送器链
	 */
	private SenderChain senderChain;
	

	/**
	 * UserInfo获取器
	 */
	private List<UserInfoGetter<UserInfo>> userInfoGetters;

	/**
	 * MessageRecord生成器
	 */
	private List<MessageRecordGetter> messageRecordGetters;

	/**
	 * 数据持久化
	 */
	private DataPersistence dataPersistence;

	/**
	 * @param senderAll  消息推送工具集合
	 * @param userInfoGetters   用户信息获取器
	 * @param messageRecordGetters   消息记录获取器
	 * @param dataPersistence      消息记录持久化对象
	 */
	public MessagePusherBuilder(SenderChain senderChain, List<UserInfoGetter<UserInfo>> userInfoGetters,
			List<MessageRecordGetter> messageRecordGetters, DataPersistence dataPersistence) {
		if (senderChain == null) {
			throw new MessageParametersException("senderChain must not be null");
		}

		if (userInfoGetters == null || userInfoGetters.isEmpty()) {
			throw new MessageParametersException("userInfoGetter must not be null or empty");
		}

		if (messageRecordGetters == null || messageRecordGetters.isEmpty()) {
			throw new MessageParametersException("messageRecordGetters must not be null or empty");
		}

		this.senderChain = senderChain;
		this.userInfoGetters = userInfoGetters;
		this.messageRecordGetters = messageRecordGetters;
		this.dataPersistence = dataPersistence;
	}

	public MessagePusher build(AbstractReceiverHolder receiverHolder) {
		return new MessagePusher(receiverHolder, senderChain, userInfoGetters, messageRecordGetters, dataPersistence);
	}

	public class MessagePusher {

		/**
		 * 消息发送器链
		 */
		private SenderChain senderChain;

		/**
		 * UserInfo获取器
		 */
		private List<UserInfoGetter<UserInfo>> userInfoGetter;

		/**
		 * MessageRecord生成器
		 */
		private List<MessageRecordGetter> messageRecordGetters;

		/**
		 * 消息接收人Holder
		 */
		private AbstractReceiverHolder receiverHolder;

		/**
		 * 数据持久化对象
		 */
		private DataPersistence dataPersistence;

		private MessagePusher(AbstractReceiverHolder receiverHolder, 
							  SenderChain senderChain,
				              List<UserInfoGetter<UserInfo>> userInfoGetter, 
				              List<MessageRecordGetter> messageRecordGetters,
				              DataPersistence dataPersistence) {

			if (receiverHolder == null) {
				throw new MessageParametersException("receiverHolder must not be null");
			}

			this.senderChain = senderChain;
			this.userInfoGetter = userInfoGetter;
			this.messageRecordGetters = messageRecordGetters;
			this.receiverHolder = receiverHolder;
			this.dataPersistence = dataPersistence;
		}
		
		

		/**
		 * 生成发送记录并且消息推送
		 * @param isPersistence 是否开启持久化
		 */
		public void sendAndIsPersistence(boolean isPersistence) {
			getMessageWrapperList(isPersistence);

		}
		
		/**
		 * 推送消息并且持久化数据
		 */
		public void sendAndPersistence() {
			getMessageWrapperList(true);
		}
		
		/**
		 * 推送消息并且不持久化数据
		 */
		public void sendAndNotPersistence() {
			getMessageWrapperList(false);
		}

		
		private void getMessageWrapperList(boolean isPersistence) {

			List<MessageWrapper> messageWrapperList = receiverHolder.getMessageWrapperList();

			if (!messageWrapperList.isEmpty()) {

				/**
				 * 存放推送记录
				 */
				List<MessageRecordWrapper> messageRecordWrapperList = new ArrayList<>();

				/**
				 * 缓存用户信息
				 */
				Map<IdType, List<UserInfo>> userInfoCache = new HashMap<>();

				/**
				 * 生成MessageRecordWrapper
				 * {@link MessageRecordWrapper}
				 */
				for (MessageWrapper messageWrapper : messageWrapperList) {
					IdType idType = messageWrapper.getIdType();

					List<UserInfo> userInfoList = null;
					if (userInfoCache.containsKey(idType)) {
						userInfoList = userInfoCache.get(idType);
					} else {
						userInfoList = getUserInfoList(idType, receiverHolder.getIdSetByIdType(idType));
						userInfoCache.put(idType, userInfoList);
					}
					
					ContentWrapper contentWrapper = messageWrapper.getContentWrapper();
					
					List<MessageRecordWrapper> mrwList = messageRecordWrapperToList(userInfoList, messageWrapper.getIdSet(), contentWrapper, idType);

					if (mrwList != null && !mrwList.isEmpty()) {
						messageRecordWrapperList.addAll(mrwList);
					}
				}
				
				/**
				 * 开始消息推送
				 */
				doSend(messageRecordWrapperList);
				
				/**
				 * 数据持久化
				 */
				if (isPersistence) {
					List<MessageRecord> result = messageRecordWrapperList.stream()
							.flatMap(e -> e.getMessageRecordList().stream()).collect(Collectors.toList());
					// 开始持久化操作
					dataPersistence.doPersistence(result);
				}
			}
		}

		/**
		 * 获取用户信息
		 * @param idType
		 * @param idSet
		 * @return
		 */
		private List<UserInfo> getUserInfoList(IdType idType, Set<Long> idSet) {
			for (UserInfoGetter<UserInfo> getter : this.userInfoGetter) {
				if (getter.supportIdType(idType)) {
					return getter.getUserInfoToList(new ArrayList<>(idSet));
				}
			}
			return null;
		}

		
		/**
		 * 生成MessageRecordWrapper 
		 * {@link MessageRecordWrapper}
		 * @param userInfoList  用户信息
		 * @param idSet			 id集合
		 * @param sendTypeAll   发送方式
		 * @param content       发送内容
		 * @param idType		 id类型
		 */
		private List<MessageRecordWrapper> messageRecordWrapperToList(List<UserInfo> userInfoList, Set<Long> idSet, ContentWrapper contentWrapper, IdType idType) {
			if (null != userInfoList && !userInfoList.isEmpty()) {
				for (MessageRecordGetter messageRecordGetter : messageRecordGetters) {
					if (messageRecordGetter.supportIdType(idType)) {
						return messageRecordGetter.doGetMessageRecordWrapper(userInfoList, idSet, contentWrapper, idType);
					}
				}
			}
			return null;
		}
		

		/**
		 * 推送消息
		 */
		private void doSend(List<MessageRecordWrapper> messageRecordWrapperList) {
			if (null != messageRecordWrapperList && !messageRecordWrapperList.isEmpty()) {
				for (MessageRecordWrapper messageRecordWrapper : messageRecordWrapperList) {
					this.senderChain.handleMessageRecord(messageRecordWrapper);
				}
			}
		}

	}
}
