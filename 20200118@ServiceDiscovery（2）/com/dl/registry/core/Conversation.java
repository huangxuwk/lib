package com.dl.registry.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.util.ThreadPoolFactory;

public class Conversation extends Communication {

	public Conversation(Socket socket, DataInputStream dis, DataOutputStream dos) {
		super();
	}

	public void dealMessage(Communication communication, String message) {
		NetMessage netMessage = new NetMessage(message);
		
		// TODO 处理注册命令
	}

	public void judgeRead() throws IOException {
		if (isReadSuccess()) {
			String message = readMessage();
			InnerDealMessage inner = new InnerDealMessage(message);
			ThreadPoolFactory.execute(new Thread(inner));
		}
	}
	/**
	 * 开启线程处理对端的消息
	 * @author dl
	 *
	 */
	private class InnerDealMessage implements Runnable {
		private String message;
		
		public InnerDealMessage(String message) {
			this.message = message;
		}

		@Override
		public void run() {
 			dealMessage(Conversation.this, message);
		}
	}
}
