package com.dl.multi_file.client;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * �˿ںų�<br>
 * 1��ʵ�ֶ˿ڵĿɷ��䣬�������Դ��������ʱ�Ķ˿ڳ�ͻ��<br>
 * 2����Դ�����߿�������һ���˿ںţ�ʹ�����黹��<br>
 * 3���ڽ����������ö�ʱ����֤�黹�˿ڣ�����˿ںŶ�ʧ��<br>
 * 4���˿ںųص���������ʼ���ǿ���ͨ���ļ����õģ�
 * @author dl
 *
 */
public class PortPool {
	public static final int DEFAULT_CAPACITY = 10;
	
	private static final Queue<Integer> portPool;
	
	static {
		portPool = new ArrayBlockingQueue<>(DEFAULT_CAPACITY);
		for (int i = 0; i < DEFAULT_CAPACITY; i++) {
			portPool.offer(59000 + i);
		}
	}
	
	public static boolean hasNext() {
		return !portPool.isEmpty();
	}
	
	public static int borrow() {
		Integer port = portPool.poll();
		if (port != null) {
			return port;
		}
		return -1;
	}
	
	public static void giveBack(int port) {
		portPool.offer(port);
	}
}
