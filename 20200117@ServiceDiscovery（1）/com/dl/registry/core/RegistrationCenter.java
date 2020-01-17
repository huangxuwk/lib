package com.dl.registry.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.dl.server.core.RPCServer;
import com.parser_reflect.util.PropertiesParser;
import com.util.ThreadPoolFactory;

public class RegistrationCenter implements Runnable {
	public static final int DEFAULT_PORT = 55555;
	
	private ServerSocket server;
	private RPCServer rpcServer;
	private int port;
	
	private IConversation conversation;
	private RoundRobin roundRobin;
	
	private volatile boolean goon;
	
	public RegistrationCenter() {
		readConfig("/RPCConfig.properties");
		rpcServer.readConfig("/RPCConfig.properties");
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public void setConversation(IConversation conversation) {
		this.conversation = conversation;
	}

	public void readConfig(String path) {
		PropertiesParser.load(path);
		
		String portStr = PropertiesParser.findElement("port");
		try {
			if (portStr != null && !portStr.equals("")) {
				int port = Integer.valueOf(portStr);
				if (port > 0 && port < 65536) {
					this.port = port;	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startup() {
		try {
			roundRobin = new RoundRobin();
			server = new ServerSocket(port);
			goon = true;
			ThreadPoolFactory.execute(new Thread(this));
			ThreadPoolFactory.execute(new Thread(roundRobin));
			rpcServer.startup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		goon = false;
		roundRobin.stopRound();
		try {
			if (!server.isClosed() && server != null) {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			server = null;
		}
		ThreadPoolFactory.shutdown(false);
		rpcServer.shutdown();
	}

	@Override
	public void run() {
		while (goon) {
			try {
				Socket socket = server.accept();
				roundRobin.addCommunication(socket, conversation);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
