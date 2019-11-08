package com.mec.cs_framework.core;

import java.net.Socket;

public class ClientConversation extends Communication {
	private Client client;
	private String id;

	ClientConversation(Client client, Socket socket) throws Exception {
		super(socket);
		if (client.getClientAction() == null) {
			close();
			throw new Exception("IClientAction未定义！");
		}
		this.client = client;
	}

	String getId() {
		return id;
	}
	
	@Override
	void dealMessage(NetMessage message) {
		// 处理来自服务器端的消息
		// 根据message中的type，分别处理来自服务器端的消息
		String para = message.getParameter();
		String action = message.getAction();
		EMessageType type = message.getType();
		switch (type) {
		case FREFUSE_ONLINE:
			close();
			client.getClientAction().refuseOnline(para);
			break;
		case YOUR_ID:
			this.id = para;
			send(new NetMessage()
					.setType(EMessageType.REQUEST_ONLINE)
					.setParameter(id));
			break;
		case CONFIRM_ONLINE:
			client.getClientAction().successOnline();
			break;
		case CONFIRM_OFFLINE:
			if (client.getClientAction().confirmOffline()) {
				send(new NetMessage()
						.setType(EMessageType.CONFIRM_OFFLINE)
						.setParameter(id));
				close();
				client.getClientAction().afterOffline();
			}
			break;
		case RESPONSE:
			client.getClientAction().dealResponse(action, para);
			break;
		default:
			break;
		}
	}

	@Override
	void peerAbnormalDrop() {
		client.getClientAction().serverAbnormalDrop();
	}

}
