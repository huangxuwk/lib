package com.mec.uddi.registry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.mec.uddi.util.MThreadPool;

public class RegistryCenter implements Runnable, INetSpeaker {
	private ServerSocket registryCenter;
	private volatile boolean goon;
	private int port;

	private NetNodeCollector nodeCollector;
	private ProviderAddressPool addressPool;
	private List<INetListener> listenerList;
	private MThreadPool mecThreadPool;
	
	private int corePoolSize;
	
	public RegistryCenter() {
		this.mecThreadPool = new MThreadPool();
	}

	public void setMaxNodeCount(int count) {
		NetNodeCollector.setCapacity(count);
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
			nodeCollector = new NetNodeCollector().start();
			addressPool = new ProviderAddressPool();
			goon = true;
			
			new Thread(this, "服务发现-注册中心").start();
		} catch (IOException e) {
			speakOut("注册中心启动失败！");
		}
		
	}

	@Override
	public void run() {
		while (goon) {
			try {
				Socket socket = registryCenter.accept();
				// 应该先将socket导入到节点池中，此时节点身份不明；
				// 同时，为了能迅速处理节点连接请求，使用一个线程完成上述任务。
				boolean linkedSuccess = nodeCollector.appendSocket(socket);
				if (!linkedSuccess) {
					try {
						socket.close();
					} catch (IOException e) {
					}
				}
			} catch (IOException e) {
				goon = false;
			}
		}
		
		close();
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
	
	private void close() {
		nodeCollector.close();
		mecThreadPool.close(true);
		try {
			if (registryCenter != null && !registryCenter.isClosed()) {
				registryCenter.close();
			}
		} catch (IOException e) {
		} finally {
			registryCenter = null;
		}
	}
	
}
