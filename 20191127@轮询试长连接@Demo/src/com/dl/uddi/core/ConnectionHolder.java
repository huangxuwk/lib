package com.dl.uddi.core;

import java.util.LinkedList;
import java.util.List;

/*
 * ����Ϊ�������ṩ���񣬱���ͨ���ŵ�
 * �����ӹ�ϵ��
 * ������ <--> �ͻ���
 * ������ <--> ע������
 * ע������ <--> �ͻ���
 * ÿ���˿ڶ�Ҫ���������������˿ڱ�������
 * �Ҷ�Ϊ˫��ͨ��
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
