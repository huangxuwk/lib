package com.mec.uddi.registry;

import java.io.IOException;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.mec.uddi.communication.Communication;
import com.mec.uddi.communication.ICommunicationAction;
import com.mec.uddi.communication.NetNodePool;

public class NetNodeCollector implements Runnable {
	public static final int DEFAULT_MAX_NODE_COUNT = 1000;
	
	private static int capacity = DEFAULT_MAX_NODE_COUNT;
	private static volatile Queue<Socket> nodeList;
	private NetNodePool netNodePool;
	private ICommunicationAction communicationAction;
	
	private volatile boolean goon;

	public NetNodeCollector() {
	}

	public static void setCapacity(int capacity) {
		NetNodeCollector.capacity = capacity;
	}
	
	public void setCommunicationAction(ICommunicationAction communicationAction) {
		this.communicationAction = communicationAction;
	}

	public void setNetNodePool(NetNodePool netNodePool) {
		this.netNodePool = netNodePool;
	}

	public NetNodeCollector start() {
		if (goon == true) {
			return this;
		}
		initQueue();
		new Thread(this, "节点收集线程").start();
		
		return this;
	}
	
	private void initQueue() {
		if (nodeList == null) {
			synchronized (NetNodeCollector.class) {
				if (nodeList == null) {
					nodeList = new ArrayBlockingQueue<>(capacity);
				}
			}
		}
	}
	
	public void close() {
		if (goon == false) {
			return;
		}
		goon = false;
	}

	public boolean appendSocket(Socket socket) {
		boolean ok = nodeList.offer(socket);
		if (ok == true) {
			synchronized (nodeList) {
				nodeList.notify();
			}
		}
		return ok;
	}
	
	@Override
	public void run() {
		// 扫描queue，若队列非空，则，处理其中的socket，生成NetNodeList
		while (goon) {
			if (nodeList.isEmpty()) {
				synchronized (nodeList) {
					try {
						nodeList.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			Socket socket = nodeList.poll();
			if (socket == null) {
				continue;
			}
			try {
				CommunicationNode cNode = new CommunicationNode(socket);
				Communication communication = new Communication(socket);
				communication.setNode(cNode);
				communication.setCommunicationAction(communicationAction);
				cNode.setCommunication(communication);
				netNodePool.addCommunicationNode(cNode);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
