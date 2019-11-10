package com.mec.cs_framework.core;

import java.io.InputStream;
import java.net.Socket;

import com.mec.util.PropertiesParser;

public class Client {
	private static final int DEFAULT_PORT = 54188;
	private static final String DEFAULT_IP = "127.0.0.1";

	private String ip;
	private int port;
	
	private Socket socket;
	private IClientAction clientAction;
	private ClientConversation conversation;
	
	public Client() {
		init();
		setClientAction(new ClientActionAdapter());
	}
	
	public boolean connectToServer() {
		if (socket != null) {
			return false;
		}
		try {
			socket = new Socket(ip, port);
			conversation = new ClientConversation(this, socket);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void offline() {
		if (clientAction.confirmOffline() == false) {
			return;
		}
		clientAction.beforeOffline();
		conversation.offline();
		clientAction.afterOffline();
	}
	
	public void toOne(String targetId, String message) {
		// 通过调用conversation层所提供的相关功能实现
		conversation.toOne(targetId, message);
	}
	
	public void toOther(String message) {
		// 通过调用conversation层所提供的相关功能实现
		conversation.toOther(message);
	}
	
	void setId(String id) {
		conversation.setId(id);
	}
	
	public String getId() {
		return conversation.getId();
	}
	
	public Client setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public Client setPort(int port) {
		this.port = port;
		return this;
	}
	
	private void init() {
		this.port = DEFAULT_PORT;
		this.ip = DEFAULT_IP;
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
			port = Integer.valueOf(str);
		}
		
		String ip = PropertiesParser.value("ip");
		if (ip.length() > 0) {
			this.ip = ip;
		}
	}
	
	public void initNetConfig(String configFilePath) {
		readCfg(configFilePath);
	}
	
	public Client setClientAction(IClientAction clientAction) {
		this.clientAction = clientAction;
		return this;
	}

	IClientAction getClientAction() {
		return clientAction;
	}
	
}
