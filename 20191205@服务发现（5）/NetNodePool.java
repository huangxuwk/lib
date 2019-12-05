package com.mec.uddi.communication;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.mec.uddi.registry.CommunicationNode;

public class NetNodePool implements Runnable {
	public static final long DEFAULT_MAX_DELAY_TIME = 10000;
	
	private static long maxDelayTime;
	private static final Queue<CommunicationNode> nodeList 
			= new ConcurrentLinkedQueue<CommunicationNode>();
	private volatile boolean goon;

	static {
		NetNodePool.maxDelayTime = DEFAULT_MAX_DELAY_TIME;
	}
	
	public NetNodePool() {
	}
	
	public static void setMaxDelayTime(long maxDelayTime) {
		NetNodePool.maxDelayTime = maxDelayTime;
	}

	public static long getMaxDelayTime() {
		return maxDelayTime;
	}

	public void addCommunicationNode(CommunicationNode node) {
		nodeList.add(node);
	}
	
	public void removeCommunicationNode(CommunicationNode node) {
		nodeList.remove(node);
	}
	
	public NetNodePool start() {
		goon = true;
		new Thread(this, "节点轮询").start();
		return this;
	}
	
	public void close() {
		goon = false;
	}

	@Override
	public void run() {
		while (goon) {
			// 轮询查询各节点发送的信息，并予以处理。
			for (CommunicationNode node : nodeList) {
				Communication communication = node.getCommunication();
				communication.checkReceive();
			}
		}
	}
	
}
