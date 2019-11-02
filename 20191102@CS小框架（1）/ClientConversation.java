package com.hx.cs_framework.core;

import java.io.IOException;
import java.net.Socket;

public class ClientConversation extends Communication {

	public ClientConversation(Socket socket) throws IOException {
		super(socket);
	}

	@Override
	public void dealMessage(String message) {
		
	}

	@Override
	public void peerAbnormalDrop() {
		System.out.println("服务器异常宕机！");
	}

}
