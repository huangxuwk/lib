package com.dl.uddi.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.dl.uddi.action.IAction;

public class Server implements Runnable {
	private ServerSocket server;
	private int port;
	private volatile boolean goon;
	
	private IAction action;
	private RoundRobin robin;
	
	public Server() {
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public IAction getAction() {
		return action;
	}

	public void setAction(IAction action) {
		this.action = action;
	}
	
	public void startup() {
		if (server == null) {
			try {
				server = new ServerSocket(port);
				robin = new RoundRobin(action);
				goon = true;
				
				new Thread(this).start();
				new Thread(robin).start();
				System.out.println("服务器启动成功");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		goon = false;
		robin.setGoon(goon);
		try {
			if (!server.isClosed() && server != null) {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			server = null;
		}
	}

	@Override
	public void run() {
		while (goon) {
			try {
				System.out.println("开启侦听");
				Socket socket = server.accept();
				System.out.println("侦听成功");				
				// 添加与遍历List不能同时执行
				synchronized (ConnectionHolder.class) {
					// 发生异常则添加不成功
					ConnectionHolder.addChannelDefinition(new ChannelDefinition(socket));
					System.out.println("client连接");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
