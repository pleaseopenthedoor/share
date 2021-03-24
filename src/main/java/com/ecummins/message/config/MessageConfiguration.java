package com.ecummins.message.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecummins.message.MessagePusherBuilder;
import com.ecummins.message.chain.SenderChain;
import com.ecummins.message.persistence.DataPersistence;
import com.ecummins.message.record.getter.MessageRecordGetter;
import com.ecummins.message.record.getter.impl.ServiceStationMessageRecordGetter;
import com.ecummins.message.record.getter.impl.ServiceWorkOrderOfTechnicianMessageRecordGetter;
import com.ecummins.message.record.getter.impl.UserMessageRecordGetter;
import com.ecummins.message.sender.MessageHandler;
import com.ecummins.message.sender.SendResult;
import com.ecummins.message.sender.Sender;
import com.ecummins.message.sender.impl.AppSender;
import com.ecummins.message.sender.impl.EmailSender;
import com.ecummins.message.sender.impl.SMSSender;
import com.ecummins.message.userinfo.UserInfo;
import com.ecummins.message.userinfo.getter.PrincipalTechnicianGetter;
import com.ecummins.message.userinfo.getter.ServiceStationGetter;
import com.ecummins.message.userinfo.getter.UserGetter;
import com.ecummins.message.userinfo.getter.UserInfoGetter;
import com.ecummins.message.userinfo.getter.UserInfoGetter.UserInfoHandler;



/**
 * 这个配置类只是一个案例
 * 当与Spring集成时，需要把配置类放到Spring容器中
 */
@Configuration
public class MessageConfiguration {


	/**
	 * 消息推送工具
	 */
	@Bean
	public Sender appSender() {
		MessageHandler messageHandler = (to, content, objects) -> {
			//PushUtils.push2Single(to, content, content);
			return new SendResult(true, String.format("%s 发送成功了", to));
		};
		return new AppSender(messageHandler);
	}

	@Bean
	public Sender emailSender() {
		MessageHandler messageHandler = (to, content, objects) -> {
			//EmailUtils.sendMail(to, objects[0].toString(), content);
			return new SendResult(true, String.format("%s 发送成功了", to));
		};
		return new EmailSender(messageHandler);
	}

	@Bean
	public Sender sMSSender() {
		MessageHandler messageHandler = (to, content, objects) -> {
			//String sendSms = SmsUtils.sendSms(to, content);
			return new SendResult(false, String.format("%s 发送失败了 发送状态:%s", to, "81"));
		};
		return new SMSSender(messageHandler);
	}

	/**
	 * 获取UserInfo工具
	 */
	@Bean
	public UserInfoHandler<UserInfo> userInfoByServiceStationIdsHandler() {
		return (idList) -> {
			System.err.println("查询用户信息ByServiceStationId");
			List<UserInfo> list = new ArrayList<>();
			UserInfo userInfo = new UserInfo();
			userInfo.setClientId("aaa");
			userInfo.setEmail("527020708@qq.com");
			userInfo.setPhone("15613015281");
			userInfo.setServiceStationId(1578L);
			userInfo.setUserId(1L);
			list.add(userInfo);
			
			UserInfo userInfo1 = new UserInfo();
			userInfo1.setClientId("bbb");
			userInfo1.setEmail("527020708@qq.com");
			userInfo1.setPhone("17319294334");
			userInfo1.setServiceStationId(1578L);
			userInfo1.setUserId(2L);
			list.add(userInfo1);
			return list;
			
		};
	}

	@Bean
	public UserInfoHandler<UserInfo> userInfoByUserIdsHandler() {
		return (idList) -> {
				System.err.println("查询用户信息ByUserId");
				List<UserInfo> list = new ArrayList<>();
				UserInfo userInfo = new UserInfo();
				userInfo.setClientId("bbb");
				userInfo.setEmail("527020708@qq.com");
				userInfo.setPhone("17319294334");
				userInfo.setServiceStationId(1578L);
				userInfo.setUserId(1L);
				list.add(userInfo);
				return list;
			};
	}

	// 获取服务工单对应的主技师
	@Bean
	public UserInfoHandler<UserInfo> userInfoByServiceWorkOrderOfTechnicianHandler() {
		return (idList) -> {
				System.err.println("查询用户信息ByUserId");
				List<UserInfo> list = new ArrayList<>();
				UserInfo userInfo = new UserInfo();
				userInfo.setClientId("bbb");
				userInfo.setEmail("527020708@qq.com");
				userInfo.setPhone("17319294334");
				userInfo.setServiceStationId(1578L);
				userInfo.setUserId(1L);
				list.add(userInfo);
				return list;
			};
	}
	
	/**
	 * 用户信息获取工具
	 * 
	 */
	// 根据serviceStationID获取站长/信息员信息
	@Bean
	public UserInfoGetter<UserInfo> serviceStationGetter() {
		return new ServiceStationGetter(userInfoByServiceStationIdsHandler());
	}

	// 根据userID获取用户信息
	@Bean
	public UserInfoGetter<UserInfo> userGetter() {
		return new UserGetter(userInfoByUserIdsHandler());
	}

	// 根据serviceWorkOrderId获取主技师信息  
	@Bean
	public UserInfoGetter<UserInfo> principalTechnicianGetter() {
		return new PrincipalTechnicianGetter(userInfoByServiceWorkOrderOfTechnicianHandler());
	}
	
	/**
	 * MessageRecordWrapper生成器
	 */
	// 根据serviceStationID获取消息记录
	@Bean
	public MessageRecordGetter serviceStationMessageRecordGetter() {
		return new ServiceStationMessageRecordGetter();
	}

	// 根据userID获取消息记录
	@Bean
	public MessageRecordGetter userMessageRecordGetter() {
		return new UserMessageRecordGetter();
	}
	
	// 根据serviceWorkOrderIdOfPrincipalTechnician获取消息记录
	@Bean
	public MessageRecordGetter serviceWorkOrderOfTechnicianMessageRecordGetter() {
		return new ServiceWorkOrderOfTechnicianMessageRecordGetter();
	}

	/**
	 * 持久化工具
	 */
	@Bean
	public DataPersistence dataPersistence() {
		return (messageRecords) -> System.err.println(messageRecords);
	}

	@Bean
	public MessagePusherBuilder messagePusherBuilder() {
		
		/**
		 * 初始化消息推送器链
		 */
		SenderChain senderChain = SenderChain.initSenderChain(sMSSender()).addSenderNode(appSender()).addSenderNode(emailSender());
		
		List<UserInfoGetter<UserInfo>> userInfoGetter = new ArrayList<>();
		userInfoGetter.add(serviceStationGetter());
		userInfoGetter.add(userGetter());
		userInfoGetter.add(principalTechnicianGetter());

		List<MessageRecordGetter> messageRecordGetters = new ArrayList<>();
		messageRecordGetters.add(serviceStationMessageRecordGetter());
		messageRecordGetters.add(userMessageRecordGetter());
		messageRecordGetters.add(serviceWorkOrderOfTechnicianMessageRecordGetter());
		
		DataPersistence dataPersistence = dataPersistence();


		return new MessagePusherBuilder(senderChain, userInfoGetter, messageRecordGetters, dataPersistence);
	}

}
