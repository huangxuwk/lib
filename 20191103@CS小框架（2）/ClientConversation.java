package com.hx.cs_framework.core;

import java.net.Socket;

public class ClientConversation extends Communication {
	private String id;
	private Client client;
	
	ClientConversation(Client client, Socket socket) throws Exception {
		super(socket);
		if (client.getClientAction() == null) {
			throw new Exception("IClientAction未定义！");
		}
		this.client = client;
	}

	@Override
	void dealMessage(NetMessage message) {
		// TODO 处理来自服务器端的消息
		// TODO 根据message中的type，分别处理来自服务器端的消息
		EMessageType type = message.getType();
		String para = message.getParameter();
//		String action = message.getAction();
		switch (type) {
		case FREFUSE_ONLINE:
			// TODO 
			// 服务器拒绝连接，就需要将通信线程关闭
			close();
			break;
		// 获得了服务器端给的id，将新的id返回给服务器完成注册
		case YOUR_ID:
			this.id = para;
			send(new NetMessage()
					.setType(EMessageType.REQUEST_ONLINE)
					.setParameter(id));
			break;
		case CONFIRM_ONLINE:
			// 调用由用户实现的successOnline()方法
			client.getClientAction().successOnline();
			break;
		default:
			break;
		}
		System.out.println("来自服务器的消息:" + message);
	}

	@Override
	void peerAbnormalDrop() {
		// TODO 处理服务器异常宕机
		System.out.println("服务器异常宕机！");
	}

}
