package com.mec.uddi.communication;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.mec.uddi.registry.CommunicationNode;

public class NetNodePool implements Runnable {
	private static final Queue<CommunicationNode> nodeList 
			= new ConcurrentLinkedQueue<CommunicationNode>();
	
	public NetNodePool() {
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
