package com.dl.rmi.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RMIServer implements Runnable {
	private int rmiPort;
	private ServerSocket server;
	private volatile boolean goon;
	
	public RMIServer() {
	}

	public RMIServer(int rmiPort) {
		this.rmiPort = rmiPort;
	}

	public void setRmiPort(int rmiPort) {
		this.rmiPort = rmiPort;
	}
	
	public void startRMIServer() {
		if (goon == true) {
			return;
		}
		try {
			server = new ServerSocket(rmiPort);
			goon = true;
			new Thread(this, "RMIServer").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shutdownRMIServer() {
		if (goon == false) {
			return;
		}
		goon = false;
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRMIServerStartup() {
		return goon;
	}
	
	@Override
	public void run() {
		while (goon) {
			try {
				Socket socket = server.accept();
				new RMIServerAcitoner(socket);
			} catch (IOException e) {
				goon = false;
			}
		}
	}
}
