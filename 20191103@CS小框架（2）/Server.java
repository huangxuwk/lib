package com.hx.cs_framework.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	// 使用map的好处在于它是键值对的形式，可以实现指定对象通信
	// 键就是id，这个id是具有重要意义的且是独有的
	private Map<String, ServerConversation> conversationMap;
	private List<INetListener> listenerList;
	
	private static int MAX_CLIENT_COUNT = 1;
	
	public Server() {
		listenerList = new ArrayList<>();
		conversationMap = new HashMap<>();
	}
	
	public void setPort(int port) {
		this.port = port;
	}

	public static int getMAX_CLIENT_COUNT() {
		return MAX_CLIENT_COUNT;
	}

	public static void setMAX_CLIENT_COUNT(int mAX_CLIENT_COUNT) {
		MAX_CLIENT_COUNT = mAX_CLIENT_COUNT;
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
	
	public void shutdown() {
		if (!goon) {
			reportMessage("服务器已宕机，无需再次宕机！");
			return;
		}
		// 还有客户端在进行通信
		if (conversationMap.size() > 0 ) {
			reportMessage("尚有客户端在线，不能宕机！");
		}
		close();
		reportMessage("服务器已宕机！");
	}
	
	boolean addServerConvasation(String id, ServerConversation serverConversation) {
		// TODO 最好检测一下，这个serverConversation最近一次连接是否频繁
		// 若连接过于频繁，则，返回false，并close这个会话
		conversationMap.put(id, serverConversation);
		return true;
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
	
	// 侦听线程，用来不断地侦听客户端的连接
	@Override
	public void run() {
		reportMessage("开始侦听客户端连接请求......");
		while (goon) {
			try {
				Socket socket = server.accept();
				// 连接一个客户端就要建立私有的会话层
				ServerConversation conversation = new ServerConversation(this ,socket);
				// 当客户端连接数达到上限， 就应该拒绝连接
				if (conversationMap.size() >= MAX_CLIENT_COUNT) {
					conversation.send(new NetMessage()
							.setType(EMessageType.FREFUSE_ONLINE)
							.setParameter("服务器已满，请稍后尝试连接！"));
					// 上面的消息是在已连接的状态下发送的，所以需要关闭连接
					conversation.close();
				} else {
					// 如果没拒接连接，就将注册的账号反馈给客户端
					conversation.sendId();
				}
			} catch (IOException e) {
				// 如果服务器关闭，就会出现这个异常
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
