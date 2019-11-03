package com.hx.cs_framework.core;

/*
 * 侦听者，即接收服务器反馈信息的
 */
public interface INetListener {
	void parseMessage(String message);
}
