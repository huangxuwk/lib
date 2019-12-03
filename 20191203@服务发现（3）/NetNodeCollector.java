package com.mec.uddi.registry;

import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class NetNodeCollector implements Runnable {
	public static final int DEFAULT_MAX_NODE_COUNT = 100;
	
	private static int capacity = DEFAULT_MAX_NODE_COUNT;
	private static volatile Queue<Socket> nodeList;
	
	private volatile boolean goon;

	public NetNodeCollector() {
	}

	public static void setCapacity(int capacity) {
		NetNodeCollector.capacity = capacity;
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
		return nodeList.offer(socket);
	}
	
	@Override
	public void run() {
		// 扫描queue，若队列非空，则，处理其中的socket，生成NetNodeList
		
	}
	
}
