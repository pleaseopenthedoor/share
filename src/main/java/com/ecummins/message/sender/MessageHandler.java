package com.ecummins.message.sender;

/**
 * 可以通过Lambda表达式把发送工具集成到对象中
 */
@FunctionalInterface
public interface MessageHandler {

	SendResult messageHandle(String to, String content, Object... objects);

}