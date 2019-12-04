package com.mec.uddi.registry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.mec.uddi.communication.ICommunicationAction;
import com.mec.uddi.communication.NetMessage;
import com.mec.uddi.communication.NetNodePool;
import com.mec.uddi.util.MThreadPool;

public class RegistryCenter implements Runnable, INetSpeaker {
	private ServerSocket registryCenter;
	private volatile boolean goon;
	private int port;

	private NetNodeCollector nodeCollector;
	private NetNodePool netNodePool;
	private List<INetListener> listenerList;
	private MThreadPool mecThreadPool;
	
	public RegistryCenter() {
		this.mecThreadPool = new MThreadPool();
		this.listenerList = new ArrayList<INetListener>();
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
			speakOut("准备启动服务注册中心……");
			registryCenter = new ServerSocket(port);
			speakOut("服务注册中心已启动！");
			netNodePool = new NetNodePool().start();
			
			nodeCollector = new NetNodeCollector();
			NetNodeCollector.setNetNodePool(netNodePool);
			nodeCollector.start();
			
			goon = true;
			speakOut("启动侦听网络节点连接请求……");
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
	
	private void speakOut(String message) {
		for (INetListener listener : listenerList) {
			listener.dealMessage(message);
		}
	}
	
	private void close() {
		nodeCollector.close();
		mecThreadPool.close(true);
		netNodePool.close();
		try {
			if (registryCenter != null && !registryCenter.isClosed()) {
				registryCenter.close();
			}
		} catch (IOException e) {
		} finally {
			registryCenter = null;
		}
	}
	
	class CommunicationAction implements ICommunicationAction {

		public CommunicationAction() {
		}
		
		@Override
		public void dealNetMessage(CommunicationNode node, NetMessage netMessage) {
			// TODO 发送过来的netMessage有如下可能性：
			// 1、服务提供者的《服务注册》请求；
			// 2、服务提供者的《服务注销》请求；
			// 3、服务消费者的《服务地址请求》。
			
		}

		@Override
		public void peerAbnormalDrop(CommunicationNode node) {
			// TODO 检查node的类型；分为3种：
			// 1、普通不明身份的网络节点；应该在netNodePool中，删除该节点；
			// 2、服务提供者；从服务提供者池中删除该节点；通知所有消费该服务的消费者，新的服务地址列表；
			// 3、服务消费者；从服务消费者池中删除该节点。
		}

		@Override
		public void removeUnknowNode(CommunicationNode node) {
			// TODO 删除不明身份节点。
		}
		
	}
	
}
