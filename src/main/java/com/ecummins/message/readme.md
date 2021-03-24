/*
	单例类：可以放入spring容器中
	所以可以通过spring注入
*/
@Autowired
private MessagePusherBuilder messagePusherBuilder;

/*
	消息包装类，包含了消息内容与发送类型的对应关系
	非单例类：每次使用时需要手动创建出来。
*/
ContentWrapper contentWrapper = ContentWrapperBuilder.initBuilder()
		.setSendTypes(SendType.EMAIL)
		.setSendTypes(SendType.SMS)
		.setContents("邮箱消息")
		.setContents("手机消息")
		.build();

/*
	持有所有消息接收人以及消息内容、消息发送方式
*/
//AllReceiverHolder receiverHolder = new AllReceiverHolder(contentWrapper, IdType.SERVICE_STATION_ID, 1L);
// ServiceStationReceiverHolder receiverHolder = new ServiceStationReceiverHolder(contentWrapper, 1L);
UserReceiverHolder receiverHolder = new UserReceiverHolder(contentWrapper, 2L);

/*
	消息发送器
	将消息推送给接收人
*/
MessagePusher pusher = messagePusherBuilder.build(receiverHolder);
#推送消息并持久化数据
pusher.sendAndPersistence();