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

	// �˷����������ӷ�����
	public void connectionToServer() {
		if (clientAction == null) {
			System.out.println("IClientActionδ���壡");
			return;
		}
		try {
			// �����ͻ���
			socket = new Socket(ip, port);
			// ���ӿͻ���
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
