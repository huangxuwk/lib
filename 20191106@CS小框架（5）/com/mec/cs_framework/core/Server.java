package com.mec.cs_framework.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server implements Runnable, INetSpeaker {
	private int port;
	private ServerSocket server;
	private volatile boolean goon;
	
	private Map<String, ServerConversation> conversationMap;
	private List<INetListener> listenerList;
	
	private IServerAction serverAction;
	
	private static int MAX_CLIENT_COUNT = 50;
	
	public Server() {
		listenerList = new ArrayList<>();
		conversationMap = new HashMap<>();
	}

	public void setServerAction(IServerAction action) {
		this.serverAction = action;
	}
	
	IServerAction getServerAction() {
		return serverAction;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public boolean isStartup() {
		return goon;
	}
	
	public void startup() {
		if (goon) {
			reportMessage("服务器已启动，无需再次启动！");
			return;
		}
		try {
			reportMessage("开始启动服务器……");
			server = new ServerSocket(port);
			reportMessage("服务器启动成功！");
			goon = true;
			new Thread(this, "侦听客户端连接请求").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void shutdown() {
		if (!goon) {
			reportMessage("服务器已宕机，无需再次宕机！");
			return;
		}
		if (conversationMap.size() > 0) {
			reportMessage("尚有客户端在线，不能宕机！");
			return;
		}
		close();
		reportMessage("服务器已宕机！");
	}
	
	public static int getMAX_CLIENT_COUNT() {
		return MAX_CLIENT_COUNT;
	}

	public static void setMAX_CLIENT_COUNT(int mAX_CLIENT_COUNT) {
		MAX_CLIENT_COUNT = mAX_CLIENT_COUNT;
	}
	
	boolean addServerConversation(String id, ServerConversation serverConversation) {
		// TODO 最好检测一下，这个serverConversation最近一次连接是否来频繁
		// 若连接过于频繁，则，返回false，并close这个会话
		conversationMap.put(id, serverConversation);
		return true;
	}
	
	void removeServerConversation(String id) {
		conversationMap.remove(id);
	}
	
	private void close() {
		goon = false;
		if (server != null) {
			try {
				server.close();
			} catch (IOException e) {
			} finally {
				server = null;
			}
		}
	}
	
	@Override
	public void run() {
		reportMessage("开始侦听客户端连接请求……");
		
		while (goon) {
			try {
				Socket socket = server.accept();
				ServerConversation conversation = new ServerConversation(this, socket);
				if (conversationMap.size() >= MAX_CLIENT_COUNT) {
					conversation.send(new NetMessage()
							.setType(EMessageType.FREFUSE_ONLINE)
							.setParameter("服务器已满，请稍后尝试连接！"));
					conversation.close();
				} else {
					conversation.sendId();
				}
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

	void reportMessage(String message) {
		for (INetListener listener : listenerList) {
			listener.parseMessage(message);
		}
	}
	
}
