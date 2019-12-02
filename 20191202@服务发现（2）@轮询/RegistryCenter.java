package com.mec.uddi.registry;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class RegistryCenter implements Runnable, INetSpeaker {
	private ServerSocket registryCenter;
	private volatile boolean goon;
	private int port;

	private ProviderPool providerPool;
	private List<INetListener> listenerList;
	
	public RegistryCenter() {
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public void startup() {
		if (goon == true) {
			speakOut("注册中心已启动！");
			return;
		}
		
		try {
			registryCenter = new ServerSocket(port);
			listenerList = new ArrayList<INetListener>();
			providerPool = new ProviderPool();
			goon = true;
		} catch (IOException e) {
			speakOut("注册中心启动失败！");
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(INetListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(INetListener listener) {
		// TODO Auto-generated method stub
		
	}
	
	private void speakOut(String message) {
		for (INetListener listener : listenerList) {
			listener.dealMessage(message);
		}
	}
	
}
