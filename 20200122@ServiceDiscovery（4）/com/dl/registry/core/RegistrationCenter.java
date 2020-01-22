package com.dl.registry.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.dl.server.core.RPCServer;
import com.parser_reflect.util.PropertiesParser;
import com.util.ThreadPoolFactory;

/**
 * 注册中心服务器<br>
 * 1、开启注册中心服务器和RPC服务器；<br>
 * 2、开启心跳检测，开启线程池；<br>
 * 3、两个服务器的ip、port都可配置，心跳时延也可配置；<br>
 * 4、开启侦听连接线程，开启轮询线程；<br>
 * 5、支持对所有的开启项进行关闭；
 * 
 * @author dl
 *
 */
public class RegistrationCenter implements Runnable {
	public static final int DEFAULT_PORT = 55555;
	
	private ServerSocket server;
	private RPCServer rpcServer;
	private int port;
	
	private IDealMessage dealMessage;
	private RoundRobin roundRobin;
	private Heartbeat heartbeat;
	
	private volatile boolean goon;
	
	public RegistrationCenter() {
		this.port = DEFAULT_PORT;
		
		heartbeat = new Heartbeat();
		rpcServer = new RPCServer();
		readConfig("/SDConfig.properties");
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public void setDealMessage(IDealMessage dealMessage) {
		this.dealMessage = dealMessage;
	}
	
	public void readConfig(String path) {
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
		
		String timeStr = PropertiesParser.findElement("heartBeatTime");
		try {
			if (timeStr != null && !portStr.equals("")) {
				int heartBeatTime = Integer.valueOf(timeStr);
				if (heartBeatTime > 0 && heartBeatTime < 65536) {
					heartbeat.setTime(heartBeatTime);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startup() {
		try {
			roundRobin = new RoundRobin();
			roundRobin.setDealMessage(dealMessage);
			server = new ServerSocket(port);
			goon = true;
			ThreadPoolFactory.execute(new Thread(this));
			ThreadPoolFactory.execute(new Thread(roundRobin));
			heartbeat.startHeartBeat();
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
		heartbeat.shutdown();
		rpcServer.shutdown();
	}

	@Override
	public void run() {
		while (goon) {
			try {
				Socket socket = server.accept();
				roundRobin.addCommunication(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
