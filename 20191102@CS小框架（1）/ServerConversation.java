package com.hx.cs_framework.core;

import java.io.IOException;
import java.net.Socket;

/**
 * 这是真正的通信层，即与客户端通信层进行通信
 * 通信层是专属于每一个对端连接的，属于服务器的一个分支
 * 这里需要处理接收的消息也要处理异常掉线的问题
 * 即，实现抽象类未实现的方法，
 * @author chaojidalao
 *
 */
public class ServerConversation extends Communication {

	public ServerConversation(Socket socket) throws IOException {
		super(socket);
		
	}

	@Override
	public void dealMessage(String message) {
		// 处理来自客户端的消息
		System.out.println("来自客户机的消息:" + message);
	}

	@Override
	public void peerAbnormalDrop() {
		System.out.println("客户端异常掉线！");
	}
	
}
