package com.mec.cs_framework.core;

import java.net.Socket;

public class Client {
	private String ip;
	private int port;
	private Socket socket;
	private ClientConversation conversation;
	private IClientAction clientAction;
	
	public Client() {
	}

	public Client setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public Client setPort(int port) {
		this.port = port;
		return this;
	}
	
	public void setClientAction(IClientAction clientAction) {
		this.clientAction = clientAction;
	}

	IClientAction getClientAction() {
		return clientAction;
	}
	
	public void sendRequest(String action, String message) {
		conversation.send(new NetMessage()
				.setType(EMessageType.REQUEST)
				.setAction(action)
				.setParameter(message));
	}
	
	public void offline() {
		conversation.send(new NetMessage()
				.setType(EMessageType.REQUEST_OFFLINE)
				.setParameter(conversation.getId()));
	}

	public void connectionToServer() {
		if (clientAction == null) {
			System.out.println("IClientAction未定义，拒绝连接服务器！");
			return;
		}
		try {
			socket = new Socket(ip, port);
			conversation = new ClientConversation(this, socket);
		} catch (Exception e) {
			clientAction.afterConnectError();
		}
	}
	
}
