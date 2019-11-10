package com.mec.cs_framework.core;

import java.net.Socket;

public class ClientConversation extends Communication {
	private Client client;
	private String id;

	public ClientConversation(Client client, Socket socket) {
		super(socket);
		this.client = client;
	}
	
	void setId(String id) {
		this.id = id;
	}
	
	String getId() {
		return id;
	}
	
	void toOne(String targetId, String message) {
		send(new NetMessage()
				.setCommand(ENetCommand.TO_ONE)
				.setAction(targetId)
				.setPara(message));
	}
	
	void toOther(String message) {
		send(new NetMessage()
				.setCommand(ENetCommand.TO_OTHER)
				.setPara(message));
	}
	
	void offline() {
		// 向服务器发送“下线”告知，并停止侦听线程
		send(new NetMessage()
				.setCommand(ENetCommand.OFFLINE));
		close();
	}

	@Override
	public void peerAbnormalDrop() {
		// 服务器异常掉线处理！
		client.getClientAction().serverAbnormalDrop();
	}

	public void dealForceDown(NetMessage message) {
		client.getClientAction().serverForcedown();
		close();
	}
	
	public void dealToOne(NetMessage message) {
		String action = message.getAction();
		String para = message.getPara();
		client.getClientAction().privateConversation(action, para);
	}
	
	public void dealOutOfRoom(NetMessage message) {
		client.getClientAction().outOfRoom();
		close();
	}
	
	public void dealId(NetMessage message) {
		setId(message.getPara());
	}
	
	@Override
	protected void dealNetMessage(NetMessage message) {
		DealNetMessage.dealCommand(this, message);
	}

}
