package com.dl.registry.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RoundRobin implements Runnable {
	private static final List<Conversation> coPool = new CopyOnWriteArrayList<>();
	
	private volatile boolean goon;
	
	public RoundRobin() {
		goon = false;
	}
	
	public void addCommunication(Socket socket) {
		
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			Conversation conversation = new Conversation(socket, dis, dos);
			coPool.add(conversation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopRound() {
		goon = false;
	}

	@Override
	public void run() {
		while (goon) {
			Iterator<Conversation> iterator = coPool.iterator();
			while (iterator.hasNext()) {
				Conversation co = iterator.next();
				try {
					co.judgeRead();
				} catch (IOException e) {
					// 通信断裂，删除该服务器
					coPool.remove(co);
				}
			}
		}
	}
}
