package com.hx.cs_framework.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 本类应该包含所有服务器的基本功能
 * 包括：打开服务器，开启侦听线程，关闭服务器
 * 后面的功能后面再进行添加
 * @author chaojidalao
 *
 */
public class Server implements Runnable, INetSpeaker {
	private int port;
	private ServerSocket server;
	private volatile boolean goon;
	// 存储所有的独有会话层，即所有与客户端的通道都在此
	private List<ServerConversation> conversationList;
	private List<INetListener> listenerList;
	
	public Server() {
		listenerList = new ArrayList<>();
		conversationList = new ArrayList<>();
	}
	
	public Server setPort(int port) {
		this.port = port;
		return this;
	}

	public void shutdown() {
		if (!goon) {
			reportMessage("服务器已宕机，无需再次宕机！");
		}
		// 还有客户端在进行通信
		if (conversationList.size() > 0 ) {
			reportMessage("尚有客户端在线，不能宕机！");
		}
		close();
		reportMessage("服务器已宕机！");
	}
	
	private void close() {
		goon = false;
		if (server != null) {
			try {
				// 服务器一关闭，侦听线程就会立即报错
				server.close();
			} catch (IOException e) {
			} finally {
				server = null;
			}
		}
	}
	
	// 打开服务器，并开启侦听线程
	public void startup() {
		// 如果服务器已启动，不能再次启动
		if (goon) {
			// 通过观察者模式，就可以显示在服务器界面上了
			// 虽然现在并不知道是哪个界面，通过哪种方式
			reportMessage("服务器已启动，无需再次启动！");
			return;
		}
		try {
			reportMessage("开始启动服务器......");
			server = new ServerSocket(port);
			reportMessage("服务器已启动！");
			goon = true;
			// 开启侦听线程
			new Thread(this, "侦听客户端连接请求").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 侦听线程，用来不断地侦听客户端的连接
	@Override
	public void run() {
		reportMessage("开始侦听客户端连接请求......");
		while (goon) {
			try {
				Socket socket = server.accept();
				//连接一个客户端就要建立私有的会话层
				new ServerConversation(socket);
			} catch (IOException e) {
				goon = false;
			}
			
		}
	}

	@Override
	public void addListener(INetListener listener) {
		if (listenerList.contains(listener)) {
			return;
		}
		listenerList.add(listener);
	}

	@Override
	public void removeListener(INetListener listener) {
		if (!listenerList.contains(listener)) {
			return;
		}
		listenerList.remove(listener);				
	}

	@Override
	public void reportMessage(String message) {
		for (INetListener listener : listenerList) {
			listener.parseMessage(message);
		}
	}

}
