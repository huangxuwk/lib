package com.mec.cs_framework.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mec.rmi.core.RMIServer;
import com.mec.util.PropertiesParser;
import com.mec.util.TimeDate;

public class Server implements Runnable, INetSpeaker {
	private static final int DEFAULT_PORT = 54188;
	private static final int MAX_CLIENT_COUNT = 50;
	private static final int DEFAULT_RMI_SERVER_PORT = 54199;
	
	private ServerSocket serverSocket;
	private int port;
	private volatile boolean goon;
	private Map<String, ServerConversation> conversationList;
	private int maxClientCount;
	
	private List<INetListener> listenerList;
	private List<Socket> socketPool;
	
	private RMIServer rmiServer;

	public Server() {
		listenerList = new ArrayList<>();
		socketPool = new ArrayList<>();
		rmiServer = new RMIServer();
		init();
	}
	
	public boolean isStartup() {
		return goon;
	}
	
	public void startup() {
		if (goon == true) {
			// 服务器已启动，无需再次启动
			speakOut("服务器已启动，无需再次启动！");
			return;
		}
		try {
			speakOut(TimeDate.getCurrentTime(TimeDate.DATE_TIME) + " 服务器启动中……");
			conversationList = new HashMap<>();
			serverSocket = new ServerSocket(port);
			rmiServer.startRMIServer();
			speakOut(TimeDate.getCurrentTime(TimeDate.DATE_TIME) + " RMI服务器已启动！");
			
			goon = true;
			new Thread(new ProcessClientConnect(), "客户端连接请求处理线程").start();
			new Thread(this, "服务器侦听线程").start();
			speakOut(TimeDate.getCurrentTime(TimeDate.DATE_TIME) + " 服务器启动成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		if (goon == false) {
			speakOut("服务器未启动！");
			return;
		}
		if (!conversationList.isEmpty()) {
			speakOut("尚有在线客户端，不能宕机！");
			return;
		}
		rmiServer.shutdownRMIServer();
		speakOut(TimeDate.getCurrentTime(TimeDate.DATE_TIME) + " RMI服务器已关闭！");
		closeConversation();
		close();
		speakOut(TimeDate.getCurrentTime(TimeDate.DATE_TIME) + " 服务器已正常关闭！");
	}
	
	public void forcedown() {
		if (goon == false) {
			speakOut("服务器未启动！");
			return;
		}
		if (!conversationList.isEmpty()) {
			// 告知所有客户端，服务器强制宕机，并处理后事！
			for (ServerConversation conversation : conversationList.values()) {
				conversation.serverForcedown();
			}
		}
		rmiServer.shutdownRMIServer();
		speakOut(TimeDate.getCurrentTime(TimeDate.DATE_TIME) + " RMI服务器已关闭！");
		closeConversation();
		close();
		speakOut(TimeDate.getCurrentTime(TimeDate.DATE_TIME) + " 强制关闭服务器！");
	}
	
	void toOne(String resourceId, String targetId, String message) {
		// 完成向指定客户端转发消息
		ServerConversation conversation = conversationList.get(targetId);
		conversation.toOne(resourceId, message);
	}
	
	void toOther(String resourceId, String message) {
		// 完成向其它所有在线客户端转发消息
		for (String id : conversationList.keySet()) {
			if (id.equals(resourceId)) {
				continue;
			}
			ServerConversation conversation = conversationList.get(id);
			conversation.toOther(resourceId, message);
		}
	}
	
	void killClient(String id) {
		ServerConversation conversation = conversationList.get(id);
		if (conversation == null) {
			speakOut("客户端[" + id + "]不存在！");
			return;
		}
		conversation.killConversation();
		synchronized (conversationList) {
			conversationList.remove(id);
		}
		speakOut("客户端[" + id + "]被强制下线！");
	}
	
	public List<String> getOnlineClient() {
		List<String> result = new ArrayList<>();
		
		synchronized (conversationList) {
			for (String id : conversationList.keySet()) {
				result.add(id);
			}
		}
		
		return result;
	}
	
	void removeConversation(String id) {
		if (!conversationList.containsKey(id)) {
			return;
		}
		conversationList.remove(id);
	}
	
	void addConversation(String id, ServerConversation conversation) {
		if (conversationList.containsKey(id)) {
			return;
		}
		conversationList.put(id, conversation);
	}
	
	private void closeConversation() {
		conversationList.clear();
		conversationList = null;
	}
	
	private void init() {
		this.port = DEFAULT_PORT;
		this.maxClientCount = MAX_CLIENT_COUNT;
		this.rmiServer.setRmiPort(DEFAULT_RMI_SERVER_PORT);
		readCfg("/net.cfg.properties");
	}
	
	private void readCfg(String cfgPath) {
		InputStream is = Server.class.getResourceAsStream(cfgPath);
		if (is == null) {
			return;
		}
		PropertiesParser.loadProperties(is);
		String str = PropertiesParser.value("port");
		if (str.length() > 0) {
			this.port = Integer.valueOf(str);
		}
		
		str = PropertiesParser.value("max_client_count");
		if (str.length() > 0) {
			this.maxClientCount = Integer.valueOf(str);
		}
		
		str = PropertiesParser.value("rmiServerPort");
		if (str.length() > 0) {
			int port = Integer.valueOf(str);
			rmiServer.setRmiPort(port);
		}
	}
	
	public void initNetConfig(String configFilePath) {
		readCfg(configFilePath);
	}
	
	@Override
	public void run() {
		
		speakOut(TimeDate.getCurrentTime(TimeDate.DATE_TIME) + " 开始侦听客户端连接请求……");
		while (goon) {
			// 侦听客户端连接，并构成、维持一个会话线程
			try {
				Socket socket = serverSocket.accept();
				synchronized (socketPool) {
					socketPool.add(socket);
					speakOut(TimeDate.getCurrentTime(TimeDate.DATE_TIME) + "客户端[" 
					+ socket.getInetAddress() + "]请求连接……");
				}
			} catch (IOException e) {
				goon = false;
			}
		}
		close();
		
	}
	
	private void close() {
		goon = false;
		try {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
		} catch (IOException e) {
		} finally {
			serverSocket = null;
		}
	}
	
	void speakOut(String message) {
		for (INetListener listener : listenerList) {
			listener.dealMessage(message);
		}
	}
	
	@Override
	public void addListener(INetListener listener) {
		if (!this.listenerList.contains(listener)) {
			this.listenerList.add(listener);
		}
	}

	@Override
	public void removeListener(INetListener listener) {
		if (this.listenerList.contains(listener)) {
			this.listenerList.remove(listener);
		}
	}
	
	class ProcessClientConnect implements Runnable {

		@Override
		public void run() {
			Socket socket = null;
			while (goon) {
				synchronized (socketPool) {
					if (socketPool.isEmpty()) {
						continue;
					}
					socket = socketPool.remove(0);
				}
				String id;
				// 应该用socket产生会话，即，ServerConversation对象！
				ServerConversation conversation = new ServerConversation(socket);
				if (conversationList.size() >= Server.this.maxClientCount) {
					conversation.send(new NetMessage()
							.setCommand(ENetCommand.OUT_OF_ROOM));
					conversation.close();
					speakOut(TimeDate.getCurrentTime(TimeDate.DATE_TIME) 
							+ "客户端[" + socket.getInetAddress() + "]因服务器满放弃连接！");
				} else {
					long curTime = System.currentTimeMillis();
					id = conversation.getIp() + ":" + curTime;
					conversation.setServer(Server.this);
					conversation.setId(id);
					conversation.send(new NetMessage()
							.setCommand(ENetCommand.ID)
							.setPara(id));
					conversationList.put(id, conversation);
					speakOut(TimeDate.getCurrentTime(TimeDate.DATE_TIME) 
							+ "客户端[" + id + "]连接成功！");
				}
				
			}
			
			socketPool.clear();
		}
		
	}

}
