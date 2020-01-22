package com.dl.registry.core;

/**
 * 处理消息的接口
 * 
 * @author dl
 *
 */
public interface IDealMessage {
	void dealMessage(NetNode netNode, String message);
}
