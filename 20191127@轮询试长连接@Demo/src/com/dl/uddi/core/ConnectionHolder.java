package com.dl.uddi.core;

import java.util.LinkedList;
import java.util.List;

/*
 * 此类为长连接提供服务，保存通信信道
 * 长连接关系表：
 * 服务器 <--> 客户端
 * 服务器 <--> 注册中心
 * 注册中心 <--> 客户端
 * 每个端口都要保持与另外两个端口保持连接
 * 且都为双向通信
 */
public class ConnectionHolder {
	private static final List<ChannelDefinition> connectionPool;
	
	static {
		connectionPool = new LinkedList<>();
	}
	
	public ConnectionHolder() {
	}
	
	public static void addChannelDefinition(ChannelDefinition channel) {
		connectionPool.add(channel);
	}
	
	public static int getSize() {
		return connectionPool.size();
	}
	
	public static ChannelDefinition getChannelDefinition(int index) {
		return connectionPool.get(index);
	}
	
	public static void remove(ChannelDefinition channel) {
		ThreadPoolFactory.execute(new RemoveThread(channel));
	}
	
	private static class RemoveThread implements Runnable {
		private ChannelDefinition channel;
		
		public RemoveThread(ChannelDefinition channel) {
			this.channel = channel;
		}

		@Override
		public void run() {
			synchronized (ConnectionHolder.class) {
				connectionPool.remove(channel);
			}
		}
		
	}
	
}
