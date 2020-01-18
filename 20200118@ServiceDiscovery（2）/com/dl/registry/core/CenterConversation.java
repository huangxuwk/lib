package com.dl.registry.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.util.ThreadPoolFactory;

/**
 * @author chaojidalao
 *
 */
public class CenterConversation extends Communication {
	private IDealMessage dealMessage;
	private NetNode netNode;
	private RoundRobin roundRobin;
	
	private volatile boolean success;

	public CenterConversation(Socket socket, DataInputStream dis, DataOutputStream dos) {
		super(socket, dis, dos);
		
		String ip = socket.getInetAddress().getHostAddress();
		int port = socket.getPort();
		NetNode netNode = new  NetNode();
		netNode.setIp(ip);
		netNode.setPort(port);
		this.netNode = netNode;
	}
	
	public NetNode getNetNode() {
		return netNode;
	}
	
	public void setRoundRobin(RoundRobin roundRobin) {
		this.roundRobin = roundRobin;
	}
	
	public void setDealMessage(IDealMessage dealMessage) {
		this.dealMessage = dealMessage;
	}

	public void dealMessage(String message) {
		System.out.println("收到的消息：" + message + "netNode:" + netNode);
		dealMessage.dealMessage(netNode, message);
	}

	public void judgeRead() throws IOException {
		if (isReadSuccess() && !success) {
			success = true;
			System.out.println("读取成功");
			// 这里可以通过标志来判断是否有任务
			InnerDealMessage inner = new InnerDealMessage();
			ThreadPoolFactory.execute(new Thread(inner));
		}
	}
	
	/**
	 * 开启线程处理对端的消息
	 * @author dl
	 *
	 */
	private class InnerDealMessage implements Runnable {

		public InnerDealMessage() {
		}	
		@Override
		public void run() {
			String message;
			try {
				message = readMessage();
				dealMessage(message);
				success = false;
			} catch (IOException e) {
				roundRobin.removeConversation(CenterConversation.this);
			}
		}
	}

}
