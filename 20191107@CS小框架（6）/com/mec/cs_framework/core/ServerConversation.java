package com.mec.cs_framework.core;

import java.io.IOException;
import java.net.Socket;

import com.mec.cs_framework.action.IAction;

public class ServerConversation extends Communication {
	private Server server;
	private String id;
	private IAction actioner;

	ServerConversation(Server server, Socket socket) throws IOException {
		super(socket);
		this.server = server;
	}
	
	void sendId() {
		String ip = socket.getInetAddress().getHostAddress();
		String id = ip + "@" + System.currentTimeMillis();
		send(new NetMessage()
				.setType(EMessageType.YOUR_ID)
				.setParameter(id));
	}
	
	void setAction(IAction actioner) {
		this.actioner = actioner;
	}
	
	@Override
	void dealMessage(NetMessage message) {
		// 处理来自客户端的消息
		// 根据message中的type和action，区分处理来自客户端的消息！
		EMessageType type = message.getType();
		String action = message.getAction();
		String para = message.getParameter();
		switch (type) {
		case REQUEST_ONLINE:
			if (!server.addServerConversation(para, this)) {
				send(new NetMessage()
						.setType(EMessageType.FREFUSE_ONLINE)
						.setParameter("连接请求过于频繁，请休息休息！"));
				close();
				return;
			}
			this.id = para;
			server.reportMessage("客户端[" + this.id + "]上线！");
			send(new NetMessage()
					.setType(EMessageType.CONFIRM_ONLINE));
			break;
		case REQUEST_OFFLINE:
			send(new NetMessage()
					.setType(EMessageType.CONFIRM_OFFLINE));
			break;
		case CONFIRM_OFFLINE:
			// 由服务器处理：在conversationMap中删除一个ServerConversation
			server.removeServerConversation(this.id);
			server.reportMessage("客户端[" + id + "]下线！");
			close();
			break;
		case REQUEST:
			try {
				Object result = actioner.doAction(action, para);
				send(new NetMessage()
						.setType(EMessageType.RESPONSE)
						.setAction(action)
						.setParameter(result.toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	@Override
	void peerAbnormalDrop() {
		server.reportMessage("客户端[" + this.id + "]异常掉线！");
		server.removeServerConversation(this.id);
	}

}
