package com.hx.cs_one2multi.serve.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class One2MultiServer implements Runnable {
	private ServerSocket serverSocket;
	private volatile boolean goon;
	private ServerConversation sc;
	private int port;
	
	public One2MultiServer() {
	}

	public One2MultiServer setPort(int port) {
		this.port = port;
		return this;
	}

	public void startup() {
		try {
			System.out.println("开始建立服务器......");
			serverSocket = new ServerSocket(port);
			System.out.println("服务器建立成功！");
			goon = true;
			new Thread(this, "客户端侦听线程").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 直接关闭服务器的操作其实是在骗自己
	// 通信信道并没有关掉
	public void shutdown() {
		try {
			serverSocket.close();
			// 调用会话层的方法来主动关闭通信信道
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 客户端侦听线程，因为侦听方法有一个弊端：没有侦听到就会一直卡在那
	@Override
	public void run() {
		System.out.println("开始侦听来自客户端的侦听请求......");
		while (goon) {
			try {
				// 侦听线程连接
				Socket client = serverSocket.accept();
				String clientIp = client.getInetAddress().getHostAddress();
				System.out.println("侦听到客户端[" + clientIp + "]的连接请求！");
				// 开启会话层，与客户端建立连接
				sc = new ServerConversation(client);
			} catch (IOException e) {
				goon = false;
			}
		}
	}
	
}
