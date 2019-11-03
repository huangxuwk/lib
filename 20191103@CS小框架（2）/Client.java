package com.hx.cs_framework.core;

import java.net.Socket;

public class Client {
	private String ip;
	private int port;
	private Socket socket;
	private ClientConversation conversation;
	private IClientAction clientAction;
	
	public Client() {
	}

	IClientAction getClientAction() {
		return clientAction;
	}

	public void setClientAction(IClientAction clientAction) {
		this.clientAction = clientAction;
	}

	public Client setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public Client setPort(int port) {
		this.port = port;
		return this;
	}

	// 此方法用来连接服务器
	public void connectionToServer() {
		if (clientAction == null) {
			System.out.println("IClientAction未定义！");
			return;
		}
		try {
			// 创建客户端
			socket = new Socket(ip, port);
			// 连接客户端
			conversation = new ClientConversation(this, socket);
		} catch (Exception e) {
			clientAction.afterConnectError();
		}
	}
	
	public void send(String message) {
		conversation.send(new NetMessage()
				.setType(EMessageType.NORMAL_MESSAGE)
				.setParameter(message));
	}
	
}
