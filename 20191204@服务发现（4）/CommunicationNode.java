package com.mec.uddi.registry;

import java.net.Socket;

import com.mec.uddi.communication.Communication;

public class CommunicationNode {
	private INetNode netNode;
	private Communication communication;
	private long linkedTime;
	
	public CommunicationNode(Socket socket) {
		this.linkedTime = System.currentTimeMillis();
		String ip = socket.getInetAddress().getHostAddress();
		int port = socket.getPort();
		this.netNode = new NetNode();
		this.netNode.setIp(ip);
		this.netNode.setPort(port);
	}

	public long getLinkedTime() {
		return linkedTime;
	}

	public void setCommunication(Communication communication) {
		this.communication = communication;
	}
	
	public INetNode getNetNode() {
		return netNode;
	}

	public Communication getCommunication() {
		return communication;
	}
	
}
