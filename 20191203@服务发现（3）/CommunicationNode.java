package com.mec.uddi.registry;

import java.net.Socket;

import com.mec.uddi.communication.Communication;

public abstract class CommunicationNode {
	private INetNode netNode;
	private Communication communication;
	
	public CommunicationNode(Socket socket) {
		
	}
	
}
