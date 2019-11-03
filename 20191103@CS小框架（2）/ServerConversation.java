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
	private Server server;
	private String id;
	
	// 每个客户端的id应该是独有的，那么就可以根据ip和当前时间确定
	// 当有客户端连接成功时就返回一个id给客户端，类似注册qq号的方式
	ServerConversation(Server server ,Socket socket) throws IOException {
		super(socket);
		this.server  = server;
	}
	
	void sendId() {
		String ip = socket.getInetAddress().getHostAddress();
		String id = ip + "@" + System.currentTimeMillis();
		send(new NetMessage()
				.setType(EMessageType.YOUR_ID)
				.setParameter(id));
	}

	@Override
	void dealMessage(NetMessage message) {
		// TODO 处理来自客户端的消息
		// TODO 根据message中的type和action，区分处理来自客户端的消息
		EMessageType type = message.getType();
		String para = message.getParameter();
		String action = message.getAction();
		switch (type) {
		// 请求加入服务器
		case REQUEST_ONLINE:
			if (!server.addServerConvasation(para, this)) {
				close();
			}
			this.id = para;
			server.reportMessage("客户端["+ para + "]上线");
			send(new NetMessage()
					.setType(EMessageType.CONFIRM_ONLINE));
			break;
		default :
			break;
		}
		System.out.println("来自客户机的消息:" + message);
	}

	@Override
	void peerAbnormalDrop() {
		System.out.println("客户端异常掉线！");
	}
	
}
